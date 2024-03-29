package eatyourbeets.cards.animator.special;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class DioBrando_TheWorld extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DioBrando_TheWorld.class)
            .SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Jojo);

    public DioBrando_TheWorld()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 0);
        SetAffinity_Star(2, 0, 0);
        SetExhaust(true);
        SetRetainOnce(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainStrength(magicNumber);
        AbstractMonster mo = GameUtilities.GetRandomEnemy(true);
        if (mo != null)
        {
            GameActions.Bottom.ApplyPower(p, mo, new StunMonsterPower(mo, 1));
        }
    }

    @Override
    public void OnUpgrade()
    {
        SetRetain(true);
    }
}