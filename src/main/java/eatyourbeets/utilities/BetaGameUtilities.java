package eatyourbeets.utilities;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.cards.base.EYBCard;

import static eatyourbeets.resources.GR.Enums.CardTags.*;
import static eatyourbeets.resources.GR.Enums.CardTags.HASTE;

public class BetaGameUtilities extends GameUtilities
{
    public static void ModifyCardTag(AbstractCard card, AbstractCard.CardTags tag, boolean value)
    {
        if (tag == null || card == null) {
            return;
        }
        EYBCard eCard = JUtils.SafeCast(card, EYBCard.class);
        if (eCard != null) {
            if (DELAYED.equals(tag)) {
                eCard.SetDelayed(value);
            }
            else if (HASTE.equals(tag)) {
                eCard.SetHaste(value);
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
