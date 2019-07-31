package expansion.cards.animator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.powers.animator.BurningPower;
import eatyourbeets.utilities.GameActionsHelper;
import eatyourbeets.cards.Synergies;
import eatyourbeets.powers.PlayerStatistics;
import expansion.cards.AnimatorCardExtension;

import java.util.Iterator;

public class KiraAndKillerQueen extends AnimatorCardExtension
    {

        public static final String ID = CreateFullID(KiraAndKillerQueen.class.getSimpleName());

    public KiraAndKillerQueen()
        {
            super(ID, 1, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

            Initialize(1,0, 4);
            this.isMultiDamage = true;

            AddExtendedDescription();

            SetSynergy(Synergies.Jojo);
        }


        @Override
        public void use(AbstractPlayer p, AbstractMonster m)
        {
        if ((float)p.currentHealth / (float)p.maxHealth < 0.25F) {
            //HP < 25 % (Bites the Dust)
            GameActionsHelper.DamageAllEnemies(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

            Iterator enm = PlayerStatistics.GetCurrentEnemies(true).iterator();
            while(enm.hasNext()) {
                AbstractMonster m1 = (AbstractMonster)enm.next();
                GameActionsHelper.ApplyPower(p, m1, new BurningPower(m1, p, 1),  this.magicNumber);
                GameActionsHelper.ApplyPower(p, m1, new BurningPower(m1, p, 1),  this.magicNumber);
                GameActionsHelper.ApplyPower(p, m1, new BurningPower(m1, p, 1),  this.magicNumber);
            }
        } else if ((float)p.currentHealth / (float)p.maxHealth < 0.50F) {
            //HP < 50 % (Sheer Heart Attack)
            GameActionsHelper.DamageAllEnemies(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

            Iterator enm = PlayerStatistics.GetCurrentEnemies(true).iterator();
            while(enm.hasNext()) {
                AbstractMonster m1 = (AbstractMonster)enm.next();
                GameActionsHelper.ApplyPower(p, m1, new BurningPower(m1, p, 1), this.magicNumber);
                GameActionsHelper.ApplyPower(p, m1, new BurningPower(m1, p, 1),  this.magicNumber);
            }
        } else {
            //HP > 50 % (Bomb Transmutation)
            GameActionsHelper.DamageAllEnemies(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

            Iterator enm = PlayerStatistics.GetCurrentEnemies(true).iterator();
            while(enm.hasNext()) {
                AbstractMonster m1 = (AbstractMonster)enm.next();
                GameActionsHelper.ApplyPower(p, m1, new BurningPower(m1, p, 1), this.magicNumber);
            }
        }



        }




        @Override
        public void upgrade()
        {
            if (TryUpgrade())
            {
                upgradeDamage(3);
                upgradeMagicNumber(1);
            }
        }

    }
