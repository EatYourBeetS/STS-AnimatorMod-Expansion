package expansion.cards.animator;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.Synergies;
import eatyourbeets.powers.PlayerStatistics;
import eatyourbeets.utilities.GameActionsHelper;
import expansion.cards.AnimatorCardExtension;

public class DioAndTheWorld extends AnimatorCardExtension
{

    public static final String ID = CreateFullID(DioAndTheWorld.class.getSimpleName());

    public DioAndTheWorld()
    {
        super(ID, 1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);

        Initialize(1,0, 0);

        AddExtendedDescription();

        SetSynergy(Synergies.Jojo);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {



    }



    @Override
    public void upgrade()
    {
        if (TryUpgrade())
        {
            //upgradeDamage(2);
        }
    }

}
