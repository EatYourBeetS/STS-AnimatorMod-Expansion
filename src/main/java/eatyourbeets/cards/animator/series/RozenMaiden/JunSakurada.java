package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.Curse_JunTormented;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class JunSakurada extends AnimatorCard
{
    public static final EYBCardData DATA = Register(JunSakurada.class)
            .SetSkill(1, AbstractCard.CardRarity.UNCOMMON, EYBCardTarget.None)
            .PostInitialize(data -> data.AddPreview(new Curse_JunTormented(), false));

    public JunSakurada()
    {
        super(DATA);

        Initialize(0, 2, 2);
        SetUpgrade(0, 0, 1);
        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Dark(0, 0, 1);

        SetUnique(true, true);
        SetEthereal(true);

        SetCooldown(2, 0, this::OnCooldownCompleted);
    }

    private void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.ModifyAllInstances(uuid, AbstractCard::upgrade)
                .IncludeMasterDeck(true)
                .IsCancellable(false);
        GameActions.Last.Purge(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.MakeCardInHand(new Curse_JunTormented());
        GameActions.Bottom.StackPower(new JunSakuradaPower(p, magicNumber));
        cooldown.ProgressCooldownAndTrigger(m);
    }

    @Override
    protected void OnUpgrade()
    {
        if (timesUpgraded % 3 == 1)
        {
            upgradeBlock(1);
        }
    }

    public static class JunSakuradaPower extends AnimatorPower
    {
        public JunSakuradaPower(AbstractCreature owner, int amount)
        {
            super(owner, JunSakurada.DATA);

            this.amount = amount;
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();
            GameActions.Bottom.SelectFromPile(name, 1, player.exhaustPile)
                    .SetFilter(c -> c.type == AbstractCard.CardType.ATTACK)
                    .AddCallback(cards -> {
                        for (AbstractCard card : cards)
                        {
                            AbstractCard copy = GameUtilities.Imitate(card);
                            if (copy.baseDamage > 0)
                            {
                                GameUtilities.IncreaseDamage(copy, amount - 1, false);
                            }
                            if (copy.baseBlock > 0)
                            {
                                GameUtilities.IncreaseBlock(copy, amount - 1, false);
                            }
                            GameActions.Bottom.PlayCopy(copy, GameUtilities.GetRandomEnemy(true));
                        }
                    });
            RemovePower();
        }

    }
}
