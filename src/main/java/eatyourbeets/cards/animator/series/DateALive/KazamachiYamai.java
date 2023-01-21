package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnAffinityGainedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class KazamachiYamai extends AnimatorCard implements OnAffinityGainedSubscriber
{
    public static final EYBCardData DATA = Register(KazamachiYamai.class).SetAttack(0, CardRarity.COMMON, EYBAttackType.Normal);

    public KazamachiYamai()
    {
        super(DATA);

        Initialize(2, 0);
        SetUpgrade(0, 0);
        SetAffinity_Red(1, 0, 0);
        SetAffinity_Green(1, 0, 0);
    }

    @Override
    public int OnAffinityGained(Affinity af, int amount)
    {
        GameActions.Bottom.Callback(() -> {
            if (af == Affinity.Blue && CombatStats.TryActivateSemiLimited(cardID))
            {
                GameActions.Bottom.MoveCard(this, player.hand);
            }
        });

        return amount;
    }

    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);
        CombatStats.onAffinityGained.Subscribe(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        if (upgraded)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        }

        if (IsStarter())
        {
            GameActions.Bottom.MakeCardInHand(makeStatEquivalentCopy());
        }
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
}