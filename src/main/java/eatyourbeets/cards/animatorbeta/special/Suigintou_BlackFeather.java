package eatyourbeets.cards.animatorbeta.special;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animatorbeta.series.RozenMaiden.Suigintou;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Suigintou_BlackFeather extends AnimatorBetaCard implements OnStartOfTurnPostDrawSubscriber
{
    public static final EYBCardData DATA = Register(Suigintou_BlackFeather.class)
    		.SetCurse(0, eatyourbeets.cards.base.EYBCardTarget.None, true)
            .SetSeries(CardSeries.RozenMaiden)
            .PostInitialize(data -> data.AddPreview(new Suigintou(), false));

    public Suigintou_BlackFeather()
    {
        super(DATA);

        Initialize(0, 0, 2, 5);
        SetUpgrade(0, 0, 0);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Dark(1, 0, 0);

        SetPurge(true);
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.FetchFromPile(name, magicNumber, player.drawPile, player.discardPile, player.exhaustPile)
                .SetFilter(c -> Suigintou.DATA.ID.equals(c.cardID));

        GameActions.Bottom.DealDamage(null, player, secondaryValue, DamageInfo.DamageType.THORNS, AttackEffects.DARK);
        for (AbstractCreature cr : GameUtilities.GetEnemies(true)) {
            GameActions.Bottom.DealDamageAtEndOfTurn(player, cr, upgraded ? secondaryValue : magicNumber, AttackEffects.DARK);
        }
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        if (player.hand.contains(this)) {
            GameActions.Bottom.DealDamageAtEndOfTurn(player, player, magicNumber, AttackEffects.DARK);
        }

    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onStartOfTurnPostDraw.Subscribe(this);
    }
}
