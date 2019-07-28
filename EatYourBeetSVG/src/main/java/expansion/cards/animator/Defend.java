package expansion.cards.animator;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.resources.Resources_Animator;
import expansion.cards.AnimatorCardExtension;
import expansion.resources.Resources_Expansion;
import eatyourbeets.utilities.GameActionsHelper;
import patches.AbstractEnums;

public class Defend  extends AnimatorCardExtension
{

    public static final String ID = CreateFullID(Defend.class.getSimpleName());

    public Defend(String id, int cost, CardTarget target)
    {
        super(Resources_Expansion.GetCardStrings(id), id, Resources_Animator.GetCardImage("animator:DefendAlt"), cost, CardType.SKILL, CardColor.COLORLESS,
                CardRarity.BASIC, target);

        //setBannerTexture("images\\cardui\\512\\banner_uncommon.png","images\\cardui\\1024\\banner_uncommon.png");

        this.tags.add(BaseModCardTags.BASIC_DEFEND);
        this.tags.add(AbstractEnums.CardTags.IMPROVED_DEFEND);
    }

    public Defend()
    {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);

        Initialize(0, 5);

        this.tags.add(BaseModCardTags.BASIC_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        GameActionsHelper.GainBlock(p, this.block);
    }

    @Override
    public void upgrade()
    {
        if (TryUpgrade())
        {
            upgradeBlock(3);
        }
    }

}
