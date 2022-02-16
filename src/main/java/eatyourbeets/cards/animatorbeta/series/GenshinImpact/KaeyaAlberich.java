package eatyourbeets.cards.animatorbeta.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.actions.orbs.TriggerOrbPassiveAbility;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class KaeyaAlberich extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(KaeyaAlberich.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.ALL);

    public KaeyaAlberich() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Blue(1, 0, 0);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {

        if (info.IsSynergizing && CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.StackPower(TargetHelper.Enemies(), PowerHelper.Shackles, magicNumber);
        }

        GameActions.Bottom.ChannelOrb(new Frost()).AddCallback(() -> {
            GameActions.Bottom.Callback(new TriggerOrbPassiveAbility(1).SetFilter(o -> Frost.ORB_ID.equals(o.ID) || Lightning.ORB_ID.equals(o.ID)));
            if (upgraded) {
                GameActions.Bottom.Callback(new TriggerOrbPassiveAbility(1));
            }
        });
    }
}