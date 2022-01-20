package eatyourbeets.cards.animatorbeta.curse;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard_Curse;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.animator.tokens.AffinityToken_Green;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.BetaActions;
import eatyourbeets.utilities.GameActions;

import static eatyourbeets.resources.BetaResources.Enums.CardTags.AUTOPLAY;

public class Curse_Delusion extends AnimatorBetaCard_Curse
{
    public static final EYBCardData DATA = Register(Curse_Delusion.class)
            .SetCurse(-2, EYBCardTarget.None, false).SetSeries(CardSeries.GenshinImpact)
            .PostInitialize(data -> data.AddPreview(AffinityToken.GetCard(Affinity.Green), true));;

    public Curse_Delusion()
    {
        super(DATA, true);
        SetAffinity_Dark(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        if (this.dontTriggerOnUseCard) {
            BetaActions.Bottom.ModifyTag(player.drawPile, 1, AUTOPLAY, true);
        }
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        AffinityToken_Green token = new AffinityToken_Green();
        token.upgrade();
        GameActions.Bottom.PlayCard(token, null);
    }

}