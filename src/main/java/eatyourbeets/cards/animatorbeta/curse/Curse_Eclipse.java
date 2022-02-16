package eatyourbeets.cards.animatorbeta.curse;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.animatorbeta.SelfImmolationPower;
import eatyourbeets.utilities.GameActions;

public class Curse_Eclipse extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(Curse_Eclipse.class)
            .SetCurse(-2, EYBCardTarget.None, false);

    public Curse_Eclipse()
    {
        super(DATA);
        Initialize(0,0,1,1);
        SetAffinity_Dark(2);
        playAtEndOfTurn = true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        if (dontTriggerOnUseCard)
        {
            GameActions.Bottom.StackPower(new SelfImmolationPower(player, magicNumber, true));
        }
    }

}