package expansion.cards.animator;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansion.cards.AnimatorCardExtension;
import expansion.resources.Resources_Expansion;
import eatyourbeets.utilities.GameActionsHelper;
import patches.AbstractEnums;

public class Strike extends AnimatorCardExtension
{
    public static final String ID = CreateFullID(Strike.class.getSimpleName());

    public Strike(String id, int cost, CardTarget target)
    {
        super(Resources_Expansion.GetCardStrings(id), id, Resources_Expansion.GetCardImage("animator:StrikeAlt"), cost, CardType.ATTACK, CardColor.COLORLESS,
                CardRarity.BASIC, target);

        //setBannerTexture("images\\cardui\\512\\banner_uncommon.png","images\\cardui\\1024\\banner_uncommon.png");

        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.tags.add(AbstractEnums.CardTags.IMPROVED_STRIKE);
    }

    public Strike()
    {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);

        Initialize(6,0);

        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        GameActionsHelper.DamageTarget(p, m, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upgrade()
    {
        if (TryUpgrade())
        {
            upgradeDamage(3);
        }
    }
}
