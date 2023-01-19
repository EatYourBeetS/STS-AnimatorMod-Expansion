package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.powers.EYBClickablePower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class MikuIzayoi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MikuIzayoi.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.None);

    private static final CardEffectChoice choices = new CardEffectChoice();

    public MikuIzayoi()
    {
        super(DATA);

        Initialize(0, 0, 3, 1);
        SetAffinity_Light(1, 1, 0);
        SetEthereal(true);

        SetAffinityRequirement(Affinity.Blue, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.GainInspiration(magicNumber);

        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.SelectFromPile(name, secondaryValue, p.drawPile)
                    .SetFilter(GameUtilities::IsSealed)
                    .AddCallback(cards ->
                    {
                        for (AbstractCard c : cards)
                        {
                            GameActions.Bottom.Motivate(c, 1);
                        }
                    });
        }
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUpgrade()
    {
        SetEthereal(false);
    }
}