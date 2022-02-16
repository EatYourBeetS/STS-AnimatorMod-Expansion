package eatyourbeets.cards.animatorbeta.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.special.RefreshHandLayout;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MitsuKusabue extends AnimatorBetaCard
{
    public static final EYBCardData DATA =
            RegisterSeriesCard(MitsuKusabue.class)
                    .SetSkill(1, AbstractCard.CardRarity.COMMON, EYBCardTarget.None);

    public MitsuKusabue() {
        super(DATA);

        Initialize(0, 0, 2, 2);
        SetUpgrade(0, 0, 0, 0);
        SetAffinity_Light(1, 1, 0);

        SetAffinityRequirement(Affinity.Light, 3);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetainOnce(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameActions.Bottom.Draw(magicNumber)
                .AddCallback(cards ->
                {
                    for (AbstractCard card : cards) {
                        GameUtilities.ModifyCostForTurn(card, 1, true);
                        GameUtilities.Retain(card);
                        if (CheckAffinity(Affinity.Light) && GameUtilities.HasLightAffinity(card)) {
                            GameActions.Bottom.GainTemporaryHP(secondaryValue);
                        }
                    }
                    GameActions.Last.Add(new RefreshHandLayout());
                });
    }
}
