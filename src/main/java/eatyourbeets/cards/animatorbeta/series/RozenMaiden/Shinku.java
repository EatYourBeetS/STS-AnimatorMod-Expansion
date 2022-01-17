package eatyourbeets.cards.animatorbeta.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shinku extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(Shinku.class)
    		.SetAttack(1, AbstractCard.CardRarity.UNCOMMON).SetSeriesFromClassPackage();

    public Shinku()
    {
        super(DATA);

        Initialize(3, 3, 2, 3);
        SetUpgrade(3, 1);
        SetAffinity_Blue(1, 1, 1);
        SetAffinity_Dark(1, 0, 0);
        SetAffinity_Light(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.DealDamage(this,m, AttackEffects.SLASH_VERTICAL);

        GameActions.Bottom.Cycle(name, magicNumber).AddCallback(() -> GameActions.Bottom.ExhaustFromPile(name, 1, p.discardPile)
                .SetFilter(GameUtilities::HasDarkAffinity)
                .SetOptions(false, true)
                .AddCallback(() ->
                {
                    GameActions.Bottom.GainTemporaryHP(secondaryValue);
                }));
    }
}


