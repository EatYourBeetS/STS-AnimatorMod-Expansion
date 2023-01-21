package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.InverseTohka;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class TohkaYatogami extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TohkaYatogami.class).SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal)
            .PostInitialize(data -> data.AddPreview(new InverseTohka(), false));

    public TohkaYatogami()
    {
        super(DATA);

        Initialize(12, 0, 2, 3);
        SetUpgrade(1, 0, 0, -1);
        SetAffinity_Red(1, 0, 0);
        SetAffinity_Blue(1, 0, 0);

        SetAffinityRequirement(Affinity.Blue, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        if (CheckAffinities(Affinity.Blue))
        {
            GameActions.Bottom.Draw(magicNumber);
            GameActions.Last.ReplaceCard(uuid, new InverseTohka()).SetUpgrade(upgraded);
        }
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        return super.ModifyDamage(enemy, amount - GetPlayerAffinity(Affinity.Blue) * secondaryValue);
    }
}