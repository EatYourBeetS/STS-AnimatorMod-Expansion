package expansion.cards.animator;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.utilities.GameActionsHelper;
import eatyourbeets.powers.PlayerStatistics;

public class Defend_Jojo extends Defend

{

    public static final String ID = CreateFullID(Defend_Jojo.class.getSimpleName());

    public Defend_Jojo()
    {
        super(ID, 1, CardTarget.SELF);

        Initialize(0, 5, 1);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        AbstractPlayer p = AbstractDungeon.player;
        PlayerStatistics.GainTemporaryStrength(p, p, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        GameActionsHelper.GainBlock(p, this.block);
    }

    @Override
    public void upgrade()
    {
        if (TryUpgrade())
        {
            upgradeBlock(3);
        }
    }


}
