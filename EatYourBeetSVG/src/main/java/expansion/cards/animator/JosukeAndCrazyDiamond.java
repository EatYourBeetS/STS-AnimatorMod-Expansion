package expansion.cards.animator;



import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import eatyourbeets.interfaces.OnEndOfTurnSubscriber;
import eatyourbeets.utilities.GameActionsHelper;
import eatyourbeets.cards.Synergies;
import eatyourbeets.powers.PlayerStatistics;
import expansion.cards.AnimatorCardExtension;


public class JosukeAndCrazyDiamond extends AnimatorCardExtension implements OnEndOfTurnSubscriber
{

    public static final String ID = CreateFullID(JosukeAndCrazyDiamond.class.getSimpleName());

    public JosukeAndCrazyDiamond()
    {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        Initialize(0,0, 4);

        this.tags.add(CardTags.HEALING);

        //AddExtendedDescription();

        SetSynergy(Synergies.Jojo);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));

        if (HasActiveSynergy())
        {
            if (PlayerStatistics.getSynergiesThisTurn() == 0)
            {
                GameActionsHelper.DrawCard(p, 1);
                //Original Synergy ?
            }
        }
    }

    @Override
    public void OnEndOfTurn(boolean isPlayer)
    {
        AbstractPlayer p = AbstractDungeon.player;

        AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(this.makeStatEquivalentCopy()));

        //End of turn effect
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
        GameActionsHelper.GainTemporaryHP(p, p, magicNumber);

        PlayerStatistics.onEndOfTurn.Unsubscribe(this);
    }


    @Override
    public void upgrade()
    {
        if (TryUpgrade())
        {
            upgradeMagicNumber(2);
        }
    }

}
