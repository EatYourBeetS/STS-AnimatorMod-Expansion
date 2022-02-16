package eatyourbeets.cards.animatorbeta.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animatorbeta.special.Suigintou_BlackFeather;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Suigintou extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(Suigintou.class)
    		.SetAttack(2, AbstractCard.CardRarity.RARE, EYBAttackType.Elemental)
            .PostInitialize(data -> data.AddPreview(new Suigintou_BlackFeather(), false));
    
    public Suigintou()
    {
        super(DATA);

        Initialize(2, 0, 1, 2);
        SetUpgrade(2, 0, 0);
        SetAffinity_Blue(2, 0, 1);
        SetAffinity_Dark(2, 0, 1);

        SetEthereal(true);
        SetExhaust(true);
        SetUnique(true, true);

        SetAffinityRequirement(Affinity.Dark, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DARK);

        GameActions.Bottom.ChannelOrbs(Dark::new, secondaryValue).AddCallback(() -> {
            if (CheckAffinity(Affinity.Dark)) {
                for (AbstractOrb orb : player.orbs) {
                    if (Dark.ORB_ID.equals(orb.ID)) {
                        GameUtilities.IncreaseOrbPassiveAmount(orb, magicNumber);
                    }
                }
            }

            GameActions.Bottom.TriggerOrbPassive(JUtils.Count(player.hand.group, GameUtilities::IsHindrance)).SetFilter(o -> Dark.ORB_ID.equals(o.ID));
        });

        GameActions.Top.MakeCardInHand(new Suigintou_BlackFeather());
    }
}
