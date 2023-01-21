package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.delegates.ActionT3;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.BetaActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class Chibimoth extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Chibimoth.class).SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new KotoriKanbe2(), false));

    public Chibimoth()
    {
        super(DATA);

        Initialize(0, 0, 2, 2);
        SetUpgrade(0, 0, 1, 0);
        SetAffinity_Star(1, 1, 0);
        SetLoyal(true);
        SetExhaust(true);
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        String[] text = DATA.Strings.EXTENDED_DESCRIPTION;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToBottom(CreateChoice(text[0], (c1, p1, m1) ->
        {
            GameActions.Bottom.GainAffinity(Affinity.Green, magicNumber, false);
            BetaActions.Bottom.GainSupportDamage(secondaryValue);
        }));
        group.addToBottom(CreateChoice(text[1], (c1, p1, m1) ->
        {
            GameActions.Bottom.GainAffinity(Affinity.Red, magicNumber, false);
            GameActions.Bottom.GainThorns(secondaryValue);
        }));

        GameActions.Bottom.SelectFromPile(name, 1, group)
                .SetOptions(false, false)
                .SetMessage(CardRewardScreen.TEXT[1])
                .AddCallback(cards ->
                {
                    for (AbstractCard card : cards)
                    {
                        card.use(player, null);
                    }
                });

        if (player.stance.ID.equals(NeutralStance.STANCE_ID) && info.TryActivateLimited())
        {
            GameActions.Bottom.MakeCardInHand(new KotoriKanbe2());
        }
    }

    private AnimatorCard_Dynamic CreateChoice(String text, ActionT3<EYBCard, AbstractPlayer, AbstractMonster> onSelect)
    {
        return ((AnimatorCardBuilder) new AnimatorCardBuilder(cardID)
                .SetImagePath(assetUrl)
                .SetProperties(CardType.SKILL, GR.Enums.Cards.THE_ANIMATOR, rarity, CardTarget.NONE)
                .SetCost(-2, 0)
                .SetNumbers(0, 0, magicNumber, secondaryValue)
                .SetOnUse(onSelect)
                .SetText(name, text, text)).Build();
    }
}