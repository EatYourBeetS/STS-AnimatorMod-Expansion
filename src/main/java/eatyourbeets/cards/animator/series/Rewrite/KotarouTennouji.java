package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.genericEffects.GenericEffect_EnterStance;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.BetaGameUtilities;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class KotarouTennouji extends AnimatorCard implements OnStanceChangedSubscriber, OnStartOfTurnPostDrawSubscriber
{
    public static final EYBCardData DATA = Register(KotarouTennouji.class).SetAttack(2, CardRarity.RARE, EYBAttackType.Normal).SetSeriesFromClassPackage();

    private static final CardEffectChoice choices = new CardEffectChoice();

    public KotarouTennouji()
    {
        super(DATA);

        Initialize(14, 0, 0);
        SetUpgrade(3, 0, 0);
        SetAffinity_Star(2, 0, 2);

        SetUnique(true, true);
    }

    @Override
    public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance)
    {
        if (player.hand.contains(this) && CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.ObtainAffinityToken(BetaGameUtilities.GetStanceAffinity(newStance), false);
            flash();
        }
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        GameEffects.Queue.ShowCardBriefly(this);
        GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
        CombatStats.onStartOfTurnPostDraw.Unsubscribe(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        if (choices.TryInitialize(this))
        {
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, ForceStance.STANCE_ID));
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, AgilityStance.STANCE_ID));
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, IntellectStance.STANCE_ID));
        }

        choices.Select(1, m);

        CombatStats.onStartOfTurnPostDraw.Subscribe((KotarouTennouji) makeStatEquivalentCopy());
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onStanceChanged.Subscribe(this);
    }
}