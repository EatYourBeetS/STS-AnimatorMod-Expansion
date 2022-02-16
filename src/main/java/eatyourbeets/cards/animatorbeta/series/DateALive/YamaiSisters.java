package eatyourbeets.cards.animatorbeta.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnSynergySubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YamaiSisters extends AnimatorBetaCard implements OnSynergySubscriber
{
    public static final EYBCardData DATA = RegisterSeriesCard(YamaiSisters.class).SetAttack(0, CardRarity.COMMON, EYBAttackType.Normal);

    public YamaiSisters()
    {
        super(DATA);

        Initialize(2, 0 );
        SetUpgrade(0, 0 );
        SetAffinity_Red(1, 0, 0);
        SetAffinity_Green(1, 0, 0);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        if (upgraded)
        {
            return super.GetDamageInfo().AddMultiplier(2);
        }

        return super.GetDamageInfo();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        if (upgraded) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        }

        if (IsStarter())
        {
            GameActions.Bottom.MakeCardInHand(makeStatEquivalentCopy());
        }
    }

    @Override
    public void OnSynergy(AbstractCard card) {
        if ((GameUtilities.HasRedAffinity(card) || GameUtilities.HasLightAffinity(card)) && CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.MoveCard(this,player.hand);
        }
    }
}