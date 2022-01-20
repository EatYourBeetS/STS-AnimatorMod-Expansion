package eatyourbeets.cards.animatorbeta.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.AnimatorBetaCard_UltraRare;
import eatyourbeets.cards.animator.series.TouhouProject.AliceMargatroid;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class RaidenShogun extends AnimatorBetaCard_UltraRare
{
    public static final EYBCardData DATA = Register(RaidenShogun.class).SetPower(3, AbstractCard.CardRarity.RARE)
            .SetMaxCopies(1)
            .SetSeries(CardSeries.GenshinImpact);

    public RaidenShogun()
    {
        super(DATA);

        Initialize(0, 0, 2, 3);
        SetUpgrade(0, 0, 0, 0);
        SetAffinity_Dark(2, 0, 0);
        SetDelayed(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();
        SetDelayed(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.ApplyPower(new RaidenShogunPower(p, this.magicNumber));
    }

    public static class RaidenShogunPower extends AnimatorPower
    {

        public RaidenShogunPower(AbstractCreature owner, int amount)
        {
            super(owner, RaidenShogun.DATA);

            Initialize(amount);
        }

        public void atStartOfTurn()
        {
            super.atStartOfTurn();
            for (AbstractOrb orb : player.orbs) {
                if (Dark.ORB_ID.equals(orb.ID) || Lightning.ORB_ID.equals(orb.ID)) {
                    GameUtilities.IncreaseOrbPassiveAmount(orb, CombatStats.Affinities.GetPowerAmount(Affinity.Dark));
                }
            }
        }


        @Override
        public void onEvokeOrb(AbstractOrb orb)
        {
            super.onEvokeOrb(orb);

            if (Dark.ORB_ID.equals(orb.ID) || Lightning.ORB_ID.equals(orb.ID)) {
                GameActions.Bottom.StackAffinityPower(Affinity.Dark, amount, false);
                flash();
            }
        }
    }
}