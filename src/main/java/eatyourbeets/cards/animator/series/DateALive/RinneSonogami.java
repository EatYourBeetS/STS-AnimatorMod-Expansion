package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnAffinitySealedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class RinneSonogami extends AnimatorCard implements OnAffinitySealedSubscriber
{
    public static final EYBCardData DATA = Register(RinneSonogami.class).SetSkill(1, CardRarity.COMMON);

    public RinneSonogami()
    {
        super(DATA);

        Initialize(0, 3, 2, 1);
        SetUpgrade(0, 0, 0);
        SetAffinity_Blue(1, 0, 2);
        SetAffinity_Light(1, 1, 1);
        SetEthereal(true);
    }

    @Override
    public void OnUpgrade()
    {
        super.OnUpgrade();
        SetEthereal(false);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);
        CombatStats.onAffinitySealed.Subscribe(this);
    }

    @Override
    public void OnAffinitySealed(EYBCard eybCard, boolean b)
    {
        if (GameUtilities.HasLightAffinity(eybCard) && player.hand.contains(this) && CombatStats.TryActivateSemiLimited(cardID))
        {
            GameActions.Bottom.ObtainAffinityToken(Affinity.Light, true);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.Retain(name, secondaryValue, false)
                .AddCallback(cards ->
                {
                   for (AbstractCard c : cards)
                   {
                       GameActions.Bottom.IncreaseScaling(c, Affinity.Blue, magicNumber);
                   }
                });
    }
}