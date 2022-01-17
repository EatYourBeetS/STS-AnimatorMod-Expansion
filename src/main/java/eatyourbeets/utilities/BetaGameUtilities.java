package eatyourbeets.utilities;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.stances.*;

import static eatyourbeets.resources.GR.Enums.CardTags.*;
import static eatyourbeets.resources.GR.Enums.CardTags.HASTE;

public class BetaGameUtilities extends GameUtilities
{
    public static Affinity GetStanceAffinity(AbstractStance stance) {
        if (ForceStance.STANCE_ID.equals(stance.ID)) {
            return Affinity.Red;
        }
        else if (AgilityStance.STANCE_ID.equals(stance.ID)) {
            return Affinity.Green;
        }
        else if (IntellectStance.STANCE_ID.equals(stance.ID)) {
            return Affinity.Blue;
        }
        else if (CorruptionStance.STANCE_ID.equals(stance.ID)) {
            return Affinity.Dark;
        }
        return Affinity.Light;
    }

    public static void ModifyCardTag(AbstractCard card, AbstractCard.CardTags tag, boolean value)
    {
        if (tag == null || card == null) {
            return;
        }
        EYBCard eCard = JUtils.SafeCast(card, EYBCard.class);
        if (eCard != null) {
            if (PURGE.equals(tag)) {
                eCard.SetPurge(value);
            }
            else if (DELAYED.equals(tag)) {
                eCard.SetDelayed(value);
            }
            else if (HASTE.equals(tag)) {
                eCard.SetHaste(value);
            }
            else if (LOYAL.equals(tag)) {
                eCard.SetLoyal(value);
            }
            else if (value) {
                eCard.tags.add(tag);
            }
            else {
                eCard.tags.remove(tag);
            }
        }
        else if (value) {
            card.tags.add(tag);
        }
        else {
            card.tags.remove(tag);
        }
    }

}
