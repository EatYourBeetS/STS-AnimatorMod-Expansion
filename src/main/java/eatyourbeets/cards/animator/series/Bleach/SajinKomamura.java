package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.EYBStance;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class SajinKomamura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SajinKomamura.class).SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None).SetSeriesFromClassPackage();

    public SajinKomamura()
    {
        super(DATA);

        Initialize(0, 7, 2, 1);
        SetUpgrade(0, 3, 0);
        SetAffinity_Red(2, 0, 0);
        SetAffinity_Green(2, 0, 1);

        SetAffinityRequirement(Affinity.Red, 2);
        SetAffinityRequirement(Affinity.Green, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        AbstractStance stance = player.stance;
        if (stance != null && !stance.ID.equals(NeutralStance.STANCE_ID) && stance instanceof EYBStance)
        {
            if (ForceStance.STANCE_ID.equals(stance.ID))
            {
                GameActions.Bottom.GainAffinity(Affinity.Red, magicNumber, false);
            }
            else if (AgilityStance.STANCE_ID.equals(stance.ID))
            {
                GameActions.Bottom.GainAffinity(Affinity.Green, magicNumber, false);
            }
            else if (IntellectStance.STANCE_ID.equals(stance.ID))
            {
                GameActions.Bottom.GainAffinity(Affinity.Blue, magicNumber, false);
            }

        }
        else if (GameUtilities.InStance(NeutralStance.STANCE_ID))
        {
            EnterRandomStanceNotCurrent();
        }

        if (TryUseAffinity(Affinity.Red) && TryUseAffinity(Affinity.Green) && CombatStats.TryActivateSemiLimited(cardID))
        {
            GameActions.Bottom.GainPlatedArmor(secondaryValue);
        }
    }

    private void EnterRandomStanceNotCurrent()
    {
        RandomizedList<String> stances = new RandomizedList<>();

        if (!player.stance.ID.equals(ForceStance.STANCE_ID))
        {
            stances.Add(ForceStance.STANCE_ID);
        }

        if (!player.stance.ID.equals(AgilityStance.STANCE_ID))
        {
            stances.Add(AgilityStance.STANCE_ID);
        }

        if (!player.stance.ID.equals(IntellectStance.STANCE_ID))
        {
            stances.Add(IntellectStance.STANCE_ID);
        }

        if (!player.stance.ID.equals(NeutralStance.STANCE_ID))
        {
            stances.Add(NeutralStance.STANCE_ID);
        }

        GameActions.Bottom.ChangeStance(stances.Retrieve(rng));
    }
}