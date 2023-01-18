package eatyourbeets.utilities;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.actions.autoTarget.ApplyPowerAuto;
import eatyourbeets.actions.cardManipulation.ModifyTag;
import eatyourbeets.actions.powers.ApplyPower;
import eatyourbeets.actions.powers.ReducePower;
import eatyourbeets.actions.special.DelayAllActions;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.animator.SupportDamagePower;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class BetaActions
{
    @Deprecated
    public static final BetaActions NextCombat = new BetaActions(GameActions.ActionOrder.NextCombat, GameActions.NextCombat);
    @Deprecated
    public static final BetaActions TurnStart = new BetaActions(GameActions.ActionOrder.TurnStart, GameActions.TurnStart);

    public static final BetaActions Instant = new BetaActions(GameActions.ActionOrder.Instant, GameActions.Instant);
    public static final BetaActions Top = new BetaActions(GameActions.ActionOrder.Top, GameActions.Top);
    public static final BetaActions Bottom = new BetaActions(GameActions.ActionOrder.Bottom, GameActions.Bottom);
    public static final BetaActions Delayed = new BetaActions(GameActions.ActionOrder.Delayed, GameActions.Delayed);
    public static final BetaActions Last = new BetaActions(GameActions.ActionOrder.Last, GameActions.Last);

    protected final GameActions.ActionOrder actionOrder;
    protected final GameActions baseActions;

    protected BetaActions(GameActions.ActionOrder actionOrder, GameActions baseActions)
    {
        this.actionOrder = actionOrder;
        this.baseActions = baseActions;
    }

    public static void ClearActions()
    {
        AbstractDungeon.actionManager.actions.clear();
    }

    public static DelayAllActions DelayCurrentActions()
    {
        return Top.Add(new DelayAllActions(true));
    }

    public <T extends AbstractGameAction> T Add(T action)
    {
        return baseActions.Add(action);
    }

    public static ArrayList<AbstractGameAction> GetActions()
    {
        return AbstractDungeon.actionManager.actions;
    }

    public ApplyPowerAuto ApplyPower(TargetHelper target, PowerHelper power, int powAmount)
    {
        return Add(new ApplyPowerAuto(target, power, powAmount));
    }

    public ApplyPower GainSupportDamage(int amount)
    {
        return StackPower(new SupportDamagePower(player, amount));
    }

    public ApplyPower StackPower(AbstractPower power)
    {
        return this.StackPower(power.owner, power);
    }

    public ApplyPower StackPower(AbstractCreature source, AbstractPower power)
    {
        return Add(new ApplyPower(source, power.owner, power, power.amount));
    }

    public ModifyTag ModifyTag(AbstractCard card, AbstractCard.CardTags tag, boolean value)
    {
        return Add(new ModifyTag(card, tag, value));
    }

    public ModifyTag ModifyTag(CardGroup group, int cards, AbstractCard.CardTags tag, boolean value)
    {
        return Add(new ModifyTag(group, cards, tag, value));
    }

    public ReducePower ReducePower(AbstractCreature target, AbstractCreature source, String powerID, int amount)
    {
        return Add(new ReducePower(target, source, powerID, amount));
    }

    public ApplyPowerAuto StackPower(TargetHelper target, PowerHelper power, int stacks)
    {
        return Add(new ApplyPowerAuto(target, power, stacks));
    }


}
