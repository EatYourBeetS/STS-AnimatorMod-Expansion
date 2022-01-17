package eatyourbeets.cards.animatorbeta.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.animatorbeta.PhasingPower;
import eatyourbeets.powers.common.CounterAttackPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SosukeAizen extends AnimatorBetaCard_UltraRare
{
    public static final EYBCardData DATA = Register(SosukeAizen.class).SetSkill(-1, CardRarity.SPECIAL, EYBCardTarget.None).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.Bleach);
    public SosukeAizen()
    {
        super(DATA);

        Initialize(0, 0, 12);
        SetUpgrade(0,0,6);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Red(2, 0, 0);

        SetMultiDamage(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int energy = GameUtilities.UseXCostEnergy(this);

        if (energy > 0)
        {
            GameActions.Bottom.StackPower(new PhasingPower(p, energy));
        }

        //CounterAttackPower.retain = true;
        GameActions.Bottom.StackPower(new CounterAttackPower(p, magicNumber));
    }
}