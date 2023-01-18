package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Traveler_Aether extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Traveler_Aether.class).SetPower(3, AbstractCard.CardRarity.RARE)
            .SetMaxCopies(1)
            .SetSeries(CardSeries.GenshinImpact);

    public Traveler_Aether()
    {
        super(DATA);

        Initialize(0, 0, 2, 3);
        SetUpgrade(0, 0, 0, 0);
        SetAffinity_Star(1, 0, 0);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.ApplyPower(new TravelerAetherPower(p, this.magicNumber));
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();
        SetDelayed(false);
    }

    public static class TravelerAetherPower extends AnimatorPower
    {

        public TravelerAetherPower(AbstractCreature owner, int amount)
        {
            super(owner, Traveler_Aether.DATA);

            Initialize(amount);
        }

        public void atStartOfTurn()
        {
            super.atStartOfTurn();
            for (AbstractOrb orb : player.orbs)
            {
                if (Dark.ORB_ID.equals(orb.ID) || Lightning.ORB_ID.equals(orb.ID))
                {
                    GameUtilities.IncreaseOrbPassiveAmount(orb, CombatStats.Affinities.GetPowerAmount(Affinity.Dark));
                }
            }
        }


        @Override
        public void onEvokeOrb(AbstractOrb orb)
        {
            super.onEvokeOrb(orb);

            if (Dark.ORB_ID.equals(orb.ID) || Lightning.ORB_ID.equals(orb.ID))
            {
                GameActions.Bottom.GainAffinity(Affinity.Dark, amount, false);
                flash();
            }
        }
    }
}