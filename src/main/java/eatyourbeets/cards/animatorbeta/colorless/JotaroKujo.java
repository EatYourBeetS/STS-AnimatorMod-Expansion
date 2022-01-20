package eatyourbeets.cards.animatorbeta.colorless;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animatorbeta.special.JotaroKujo_StarPlatinum;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.BetaActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import static eatyourbeets.resources.BetaResources.Enums.CardTags.AUTOPLAY;

public class JotaroKujo extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(JotaroKujo.class)
            .SetSkill(3, AbstractCard.CardRarity.RARE, eatyourbeets.cards.base.EYBCardTarget.None)
            .SetColor(AbstractCard.CardColor.COLORLESS).SetSeries(CardSeries.Jojo)
            .PostInitialize(data -> data.AddPreview(new JotaroKujo_StarPlatinum(), false));

    private int turns;

    public JotaroKujo()
    {
        super(DATA);

        Initialize(0, 18, 1, 0);

        SetAffinity_Red(1,0,2);
        SetAffinity_Light(1, 0, 1);

        SetCooldown(4, 0, this::OnCooldownCompleted);
    }

    @Override
    public void OnUpgrade() {
        super.OnUpgrade();
        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.FetchFromPile(name, 1, player.drawPile)
        .SetOptions(true, false)
            .SetFilter(c -> c.cost >= 2)
            .AddCallback(cards ->
            {
                for (AbstractCard c : cards)
                {
                    GameUtilities.Retain(c);
                }
            });

        cooldown.ProgressCooldownAndTrigger(m);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.Exhaust(this);
        GameActions.Bottom.MakeCardInHand(new JotaroKujo_StarPlatinum());
    }
}