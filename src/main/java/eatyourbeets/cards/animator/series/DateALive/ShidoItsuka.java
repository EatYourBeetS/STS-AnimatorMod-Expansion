package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.OrbCore_Frost;
import eatyourbeets.cards.animator.tokens.AffinityToken_Blue;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.ArrayList;

public class ShidoItsuka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ShidoItsuka.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.None);

    protected final static ArrayList<AbstractCard> blueCards = new ArrayList<>();

    public ShidoItsuka()
    {
        super(DATA);

        Initialize(0, 8, 3);
        SetUpgrade(0, 0);
        SetAffinity_Blue(1, 0, 0);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        InitializeSynergicCards();

        RandomizedList<AbstractCard> randomizedSynergicCards = new RandomizedList<>(blueCards);

        final CardGroup options = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        for (int i = 0; i < magicNumber; i++)
        {
            AbstractCard randomCard;
            if (randomizedSynergicCards.Size() == 0)
            {
                break;
            }

            randomCard = randomizedSynergicCards.Retrieve(rng, true).makeCopy();

            if (upgraded)
            {
                randomCard.upgrade();
            }

            options.addToBottom(randomCard);
        }

        GameActions.Top.SelectFromPile(name, 1, options)
                .SetOptions(false, false)
                .AddCallback(cards ->
                {
                    if (cards.size() > 0)
                    {
                        if (info.IsSynergizing)
                        {
                            GameActions.Bottom.MakeCardInDrawPile(cards.get(0))
                                    .SetDuration(Settings.ACTION_DUR_FASTER, true);
                        }
                        else
                        {
                            GameActions.Bottom.MakeCardInDiscardPile(cards.get(0))
                                    .SetDuration(Settings.ACTION_DUR_FASTER, true);
                        }

                    }
                });

        if (info.IsSynergizing && CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Last.ModifyAllInstances(uuid, c -> ((EYBCard) c).SetExhaust(true));
        }
    }

    private void InitializeSynergicCards()
    {
        blueCards.clear();

        for (AbstractCard c : CardLibrary.getAllCards())
        {
            // Certain special cards are allowed
            if (c instanceof OrbCore_Frost || c instanceof AffinityToken_Blue)
            {
                blueCards.add(c);
            }

            else if (c instanceof AnimatorCard
                    && !GameUtilities.IsHindrance(c)
                    && GameUtilities.IsObtainableInCombat(c)
                    && GameUtilities.HasBlueAffinity(c))
            {
                blueCards.add(c);
            }
        }
    }
}