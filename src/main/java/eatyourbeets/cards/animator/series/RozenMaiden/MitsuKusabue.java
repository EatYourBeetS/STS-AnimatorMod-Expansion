package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.special.RefreshHandLayout;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MitsuKusabue extends AnimatorCard
{
    public static final EYBCardData DATA =
            Register(MitsuKusabue.class)
                    .SetSkill(1, AbstractCard.CardRarity.COMMON, EYBCardTarget.None)
                    .SetSeriesFromClassPackage();

    public MitsuKusabue()
    {
        super(DATA);

        Initialize(0, 0, 2, 2);
        SetUpgrade(0, 0, 0, 0);
        SetAffinity_Light(1, 1, 0);

        SetAffinityRequirement(Affinity.Light, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Draw(magicNumber)
                .AddCallback(cards ->
                {
                    for (AbstractCard card : cards)
                    {
                        GameUtilities.ModifyCostForTurn(card, 1, true);
                        GameUtilities.Retain(card);
                        if (TryUseAffinity(Affinity.Light) && GameUtilities.HasLightAffinity(card))
                        {
                            GameActions.Bottom.GainTemporaryHP(secondaryValue);
                        }
                    }
                    GameActions.Last.Add(new RefreshHandLayout());
                });
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetainOnce(true);
    }
}
