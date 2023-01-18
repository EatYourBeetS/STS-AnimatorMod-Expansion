package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.uncommon.QuestionMark;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameUtilities;

public class UltimateCheatCard extends AnimatorCard
{
    public static final EYBCardData DATA = Register(UltimateCheatCard.class)
            .SetSkill(0, CardRarity.SPECIAL, eatyourbeets.cards.base.EYBCardTarget.None)
            .SetImagePath(QuestionMark.DATA.ImagePath);

    public UltimateCheatCard()
    {
        super(DATA);

        Initialize(0, 0, 3, 3);
        SetUpgrade(0, 0, 1);
        SetAffinity_Star(1, 0, 0);
        SetRetainOnce(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        if (GR.Animator.Data.SpecialTrophies.Trophy1 < 0)
        {
            GR.Animator.Data.SpecialTrophies.Trophy1 = 20;
            GameUtilities.GetAscensionData(true).OnTrueVictory();
        }
        GR.Animator.Data.RecordTrueVictory(GameUtilities.GetActualAscensionLevel());

    }
}