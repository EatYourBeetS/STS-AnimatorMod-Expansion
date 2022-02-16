package eatyourbeets.resources;

import com.megacrit.cardcrawl.cards.tempCards.Beta;
import com.megacrit.cardcrawl.localization.UIStrings;
import eatyourbeets.resources.animator.AnimatorStrings;

public class BetaStrings
{
    public BetaStrings.Series Series;

    public BetaStrings() {
    }

    public void Initialize() {
        this.Series = new BetaStrings.Series();
    }

    public class Series {
        private final UIStrings Strings = BetaStrings.GetUIStrings("Series");
        public final String Series;

        public Series() {
            this.Series = this.Strings.EXTRA_TEXT[0];
        }

        public final String SeriesName(int seriesID) {
            return this.Strings.TEXT.length > seriesID ? this.Strings.TEXT[seriesID] : null;
        }
    }

    private static UIStrings GetUIStrings(String id) {
        return GR.GetUIStrings(GR.CreateID(BetaResources.BASE_PREFIX, id));
    }
}
