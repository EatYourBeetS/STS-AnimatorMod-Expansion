package eatyourbeets.cards.animator.colorless;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.DioBrando_TheWorld;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

public class DioBrando extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DioBrando.class)
            .SetAttack(3, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS).SetSeries(CardSeries.Jojo)
            .PostInitialize(data -> data.AddPreview(new DioBrando_TheWorld(), false));

    private int turns;

    public DioBrando()
    {
        super(DATA);

        Initialize(19, 0, 2);
        SetUpgrade(4, 0, 0);

        SetAffinity_Red(2, 0, 2);
        SetAffinity_Dark(1, 0, 1);

        SetCooldown(4, 0, this::OnCooldownCompleted);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.Exhaust(this);
        GameActions.Bottom.MakeCardInHand(new DioBrando_TheWorld());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SLASH_HEAVY).SetVFXColor(Color.GOLDENROD, Color.GOLDENROD).SetSoundPitch(0.5f, 1.5f);

        GameActions.Bottom.Draw(magicNumber).AddCallback(() -> {
            GameActions.Bottom.SelectFromHand(name, player.hand.size(), false)
                    .SetOptions(true, true, true)
                    .SetMessage(GR.Common.Strings.HandSelection.MoveToDrawPile)
                    .AddCallback(cards ->
                    {
                        for (int i = cards.size() - 1; i >= 0; i--)
                        {
                            GameActions.Top.MoveCard(cards.get(i), player.hand, player.drawPile);
                        }
                        GameActions.Top.GainTemporaryHP(cards.size());
                    });
        });

        cooldown.ProgressCooldownAndTrigger(m);
    }

    @Override
    public void OnUpgrade()
    {
        super.OnUpgrade();
        SetInnate(true);
    }
}