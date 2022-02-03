package eatyourbeets.cards.animatorbeta.special;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animator.series.OnePunchMan.Saitama;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class DioBrando_TheWorld extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(DioBrando_TheWorld.class).SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None).SetSeries(CardSeries.Jojo);

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
    public void OnUpgrade() {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainStrength(magicNumber);
        AbstractMonster mo = GameUtilities.GetRandomEnemy(true);
        if (mo != null) {
            GameActions.Bottom.ApplyPower(p, mo, new StunMonsterPower(mo, 1));
        }
    }
}