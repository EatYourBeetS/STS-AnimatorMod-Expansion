package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnEndOfTurnFirstSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.common.DelayedDamagePower;
import eatyourbeets.utilities.BetaActions;
import eatyourbeets.utilities.GameActions;

public class NoriSakurada extends AnimatorCard
{
    public static final EYBCardData DATA =
            Register(NoriSakurada.class)
                    .SetSkill(0, AbstractCard.CardRarity.COMMON, eatyourbeets.cards.base.EYBCardTarget.None);

    public NoriSakurada()
    {
        super(DATA);

        Initialize(0, 0, 1, 2);
        SetUpgrade(0, 0, 0, 0);
        SetAffinity_Light(1, 0, 0);

        SetAffinityRequirement(Affinity.Light, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {

        GameActions.Top.FetchFromPile(name, 1, player.discardPile)
                .SetOptions(true, false);
        GameActions.Bottom.DiscardFromHand(name, 1, false);

        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.Retain(name, 1, false);
        }
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }
}
