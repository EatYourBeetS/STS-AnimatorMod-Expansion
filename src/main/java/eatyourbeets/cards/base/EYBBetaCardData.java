package eatyourbeets.cards.base;

import com.megacrit.cardcrawl.localization.CardStrings;

public class EYBBetaCardData extends EYBCardData
{
    public EYBBetaCardData(Class<? extends EYBCard> type, String cardID)
    {
        super(type, cardID);
    }

    public EYBBetaCardData(Class<? extends EYBCard> type, String cardID, CardStrings strings) {
        super(type, cardID, strings);
    }

    public EYBCardData SetSeriesFromClassPackage() {
        int length = "eatyourbeets.cards.animatorbeta.".length();
        String name = this.type.getPackage().getName();
        CardSeries series = CardSeries.GetByName(name.substring(length + ("series.").length()), false);
        if (series == null) {
            throw new RuntimeException("Couldn't find card series from class package: " + this.type);
        } else {
            return this.SetSeries(series);
        }
    }
}
