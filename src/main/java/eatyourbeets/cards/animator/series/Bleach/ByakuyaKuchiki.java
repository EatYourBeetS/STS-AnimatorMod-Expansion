package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.special.ByakuyaBankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.genericEffects.GenericEffect_EnterStance;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.delegates.ActionT3;
import eatyourbeets.resources.GR;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;

public class ByakuyaKuchiki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ByakuyaKuchiki.class).SetAttack(3, CardRarity.RARE, EYBAttackType.Piercing)
            .PostInitialize(data -> data.AddPreview(new ByakuyaBankai(), false));

    private static final CardEffectChoice choices = new CardEffectChoice();


    public ByakuyaKuchiki()
    {
        super(DATA);

        Initialize(10, 10, 0);
        SetUpgrade(2, 2, 0);
        SetAffinity_Red(2, 0, 0);
        SetAffinity_Green(2, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {


        if (ForceStance.IsActive() || AgilityStance.IsActive())
        {
            GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
            GameActions.Bottom.MakeCardInDrawPile(new ByakuyaBankai());
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
        else
        {
            GameActions.Bottom.Callback(m, (enemy, __) -> {
                ChooseAction(enemy);
            });
        }
    }

    private void ChooseAction(AbstractMonster m)
    {
        if (choices.TryInitialize(this))
        {
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, AgilityStance.STANCE_ID));
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, ForceStance.STANCE_ID));
        }

        choices.Select(GameActions.Top, 1, m)
                .CancellableFromPlayer(true);
    }
}