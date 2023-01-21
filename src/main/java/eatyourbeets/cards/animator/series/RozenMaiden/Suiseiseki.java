package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.status.Status_Slimed;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;

public class Suiseiseki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Suiseiseki.class)
            .SetSkill(1, AbstractCard.CardRarity.COMMON, eatyourbeets.cards.base.EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Status_Slimed(), false).AddPreview(new Souseiseki(), false));

    public Suiseiseki()
    {
        super(DATA);

        Initialize(0, 7, 4, 3);
        SetUpgrade(0, 3, 0);

        SetAffinity_Green(1, 0, 0);
        SetAffinity_Light(1, 0, 1);

        SetAffinityRequirement(Affinity.Green, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryHP(magicNumber);

        Status_Slimed slimed = new Status_Slimed();
        if (TryUseAffinity(Affinity.Green))
        {
            slimed.retain = true;
        }
        GameActions.Bottom.MakeCard(slimed, player.drawPile);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }
}
