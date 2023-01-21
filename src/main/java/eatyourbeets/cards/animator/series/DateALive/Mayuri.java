package eatyourbeets.cards.animator.series.DateALive;

import com.badlogic.gdx.Game;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Mayuri extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Mayuri.class).SetSkill(2, CardRarity.COMMON).SetSeriesFromClassPackage();

    public Mayuri()
    {
        super(DATA);

        Initialize(0, 7, 2, 2);
        SetUpgrade(0, 3, 0);
        SetAffinity_Light(1, 1, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ChannelOrbs(Lightning::new, magicNumber);

        if (JUtils.Any(GameUtilities.GetEnemies(true), mo -> GameUtilities.IsAttacking(mo.intent)))
        {
            GameActions.Bottom.TriggerOrbPassive(secondaryValue);
        }
    }
}