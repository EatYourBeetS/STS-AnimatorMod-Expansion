package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shinku extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shinku.class)
            .SetSkill(1, AbstractCard.CardRarity.UNCOMMON);

    public Shinku()
    {
        super(DATA);

        Initialize(0, 5, 1, 3);
        SetUpgrade(0, 1);
        SetAffinity_Blue(1, 1, 1);
        SetAffinity_Dark(1, 0, 0);
        SetAffinity_Light(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.Cycle(name, magicNumber).AddCallback(() -> GameActions.Bottom.ExhaustFromPile(name, 1, p.discardPile)
                .SetFilter(GameUtilities::IsSealed)
                .SetOptions(false, true)
                .AddCallback(() ->
                {
                    GameActions.Bottom.GainTemporaryHP(secondaryValue);
                }));
    }
}


