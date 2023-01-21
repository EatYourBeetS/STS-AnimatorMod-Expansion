package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class Kanaria_Pizzicato extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Kanaria_Pizzicato.class)
            .SetSkill(0, AbstractCard.CardRarity.SPECIAL, eatyourbeets.cards.base.EYBCardTarget.None)
            .SetSeries(CardSeries.RozenMaiden);

    public Kanaria_Pizzicato()
    {
        super(DATA);

        Initialize(0, 0, 3, 3);
        SetUpgrade(0, 0, 1);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(1, 0, 0);
        SetRetainOnce(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DiscardFromHand(name, 1, false).AddCallback(() -> GameActions.Bottom.Draw(1).AddCallback(cards -> {
            for (AbstractCard c : cards)
            {
                switch (c.type)
                {
                    case ATTACK:
                        GameActions.Bottom.ApplyPoison(TargetHelper.Enemies(), magicNumber);
                        break;
                    case POWER:
                        GameActions.Bottom.ChannelOrb(new Aether());
                        break;
                    default:
                        GameActions.Bottom.GainAffinity(Affinity.Green,1, true);
                }
            }
        }));
    }
}