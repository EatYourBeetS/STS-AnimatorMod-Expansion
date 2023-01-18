package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.CorruptionStance;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;
import eatyourbeets.utilities.RandomizedList;

public class AkiraInoue extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AkiraInoue.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.Self);

    public AkiraInoue()
    {
        super(DATA);

        Initialize(0, 2, 2, 6);
        SetUpgrade(0, 0, 2);
        SetAffinity_Green(1, 0, 0);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        final RandomizedList<AbstractCard> pile = new RandomizedList<>(JUtils.Filter(player.discardPile.group, c -> !GameUtilities.IsHindrance(c)));
        if (pile.Size() == 0)
        {
            pile.AddAll(player.discardPile.group);
        }
        while (group.size() < magicNumber && pile.Size() > 0)
        {
            group.addToTop(pile.Retrieve(rng));
        }

        if (group.size() > 0)
        {
            GameActions.Bottom.FetchFromPile(name, 1, group)
                    .SetOptions(false, true).AddCallback(cards -> {
                        for (AbstractCard c : cards)
                        {
                            GameActions.Bottom.Motivate(c, 1);
                        }
                    });
        }

        if (!player.stance.ID.equals(NeutralStance.STANCE_ID) && info.TryActivateSemiLimited())
        {
            if (ForceStance.STANCE_ID.equals(player.stance.ID))
            {
                GameActions.Bottom.ObtainAffinityToken(Affinity.Red, false);
            }
            else if (AgilityStance.STANCE_ID.equals(player.stance.ID))
            {
                GameActions.Bottom.ObtainAffinityToken(Affinity.Green, false);
            }
            else if (IntellectStance.STANCE_ID.equals(player.stance.ID))
            {
                GameActions.Bottom.ObtainAffinityToken(Affinity.Blue, false);
            }
            else if (CorruptionStance.STANCE_ID.equals(player.stance.ID))
            {
                GameActions.Bottom.ObtainAffinityToken(Affinity.Dark, false);
            }
        }
    }
}