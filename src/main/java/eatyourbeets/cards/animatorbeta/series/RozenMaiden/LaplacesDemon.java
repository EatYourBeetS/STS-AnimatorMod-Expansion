package eatyourbeets.cards.animatorbeta.series.RozenMaiden;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class LaplacesDemon extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(LaplacesDemon.class)
    		.SetSkill(2, CardRarity.RARE, EYBCardTarget.None).SetSeriesFromClassPackage();

    public LaplacesDemon()
    {
        super(DATA);

        Initialize(0, 0, 3, 6);
        SetUpgrade(0, 0, 0, 1);

        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Dark(2, 0, 0);

        SetDelayed(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int hpLoss = GetHandAffinity(Affinity.General, false) * magicNumber;
        int[] damageMatrix = DamageInfo.createDamageMatrix(hpLoss, true);
        GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.HP_LOSS, AttackEffects.NONE);

        GameActions.Bottom.SelectFromHand(name, player.hand.size(), true)
                .SetOptions(true, true, true)
                .AddCallback(cards -> {
                    for (AbstractCard card : cards) {
                        EYBCard eC = JUtils.SafeCast(card, EYBCard.class);
                        if (eC != null) {
                            int affinities = 0;
                            for (Affinity af : Affinity.Basic()) {
                                // Increase the chance of an affinity winding up as 0
                                int val = Math.max(0, MathUtils.random(-2,2));
                                if (val > 0) {
                                    affinities += 1;
                                }
                                GameActions.Last.ModifyAffinityLevel(eC, af, val, false);

                                // Do not let any card have more than 3 affinities
                                if (affinities >= 3) {
                                    break;
                                }
                            }
                            if (affinities == 0) {
                                GameActions.Last.ModifyAffinityLevel(eC, Affinity.Star, MathUtils.random(1,2), false);
                            }
                        }
                    }
                });
    }
}
