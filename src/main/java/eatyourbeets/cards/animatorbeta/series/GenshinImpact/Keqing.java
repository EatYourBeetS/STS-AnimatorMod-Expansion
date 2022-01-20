package eatyourbeets.cards.animatorbeta.series.GenshinImpact;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnEvokeOrbSubscriber;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.*;

public class Keqing extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(Keqing.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing)
            .SetMaxCopies(2)
            .SetSeriesFromClassPackage();
    public boolean canGive = true;

    public Keqing()
    {
        super(DATA);

        Initialize(2, 0, 1);
        SetUpgrade(1, 0, 0);
        SetAffinity_Green(2, 0, 2);
        SetAffinity_Dark(1, 0, 1);

        SetCooldown(4, 0, this::OnCooldownCompleted);

        SetExhaust(true);
    }

    public void update() {
        super.update();
        if (hasTag(HASTE)) {
            canGive = true;
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (canGive) {
            canGive = false;
            GameActions.Top.ChannelOrbs(Lightning::new, magicNumber).AddCallback(orbs -> {
                for (AbstractOrb orb : orbs) {
                    GameActions.Bottom.TriggerOrbPassive(orb, 1);
                }
            });
        }
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER).SetDamageEffect(c -> GameEffects.List.Add(new DieDieDieEffect()).duration);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.MoveCard(this, player.exhaustPile, player.drawPile)
                .ShowEffect(true, false);
        BetaActions.Bottom.ModifyTag(this, HASTE, true);
        canGive = true;
    }
}