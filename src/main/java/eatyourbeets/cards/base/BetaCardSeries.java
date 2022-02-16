package eatyourbeets.cards.base;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Beta;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.BetaResources;
import eatyourbeets.utilities.FieldInfo;
import eatyourbeets.utilities.JUtils;

import java.net.JarURLConnection;
import java.util.*;

public class BetaCardSeries extends CardSeries
{
    private final static HashMap<Integer, CardSeries> betaIDs = new HashMap<>();

    private static final FieldInfo<Map<Integer, CardSeries>> _mapIDs = JUtils.GetField("mapIDs", CardSeries.class);
    private static final FieldInfo<Map<String, CardSeries>> _mapNames = JUtils.GetField("mapNames", CardSeries.class);

    public final static CardSeries CowboyBebop = Add(52, "CowboyBebop");
    public final static CardSeries GhostInTheShell = Add(53, "GhostInTheShell");
    public final static CardSeries Clannad = Add(54, "Clannad");
    public final static CardSeries GurrenLagann = Add(55, "GurrenLagann");
    public final static CardSeries Gundam = Add(56, "Gundam");
    public final static CardSeries Evangelion = Add(57, "Evangelion");
    public final static CardSeries Lupin = Add(58, "Lupin");
    public final static CardSeries Kirby = Add(59, "Kirby");
    public final static CardSeries Xenoblade = Add(60, "Xenoblade");
    public final static CardSeries HoukaiGakuen = Add(61, "HoukaiGakuen");
    public final static CardSeries Mushishi = Add(62, "Mushishi");
    public final static CardSeries NatsumeYuujinchou = Add(63, "NatsumeYuujinchou");
    public final static CardSeries Hololive = Add(64, "Hololive");
    public final static CardSeries Berserk = Add(65, "Berserk");
    public final static CardSeries DrStone = Add(66, "DrStone");
    public final static CardSeries Kaiji = Add(67, "Kaiji");
    public final static CardSeries Trigun = Add(68, "Trigun");
    public final static CardSeries PantyStocking = Add(69, "PantyStocking");
    public final static CardSeries Danganronpa = Add(70, "Danganronpa");
    public final static CardSeries PhoenixWright = Add(71, "PhoenixWright");
    public final static CardSeries Magi = Add(72, "Magi");
    public final static CardSeries Monster = Add(73, "Monster");
    public final static CardSeries EurekaSeven = Add(74, "EurekaSeven");
    public final static CardSeries EightySix = Add(75, "EightySix");
    public final static CardSeries Nier = Add(76, "Nier");
    public final static CardSeries Megaman = Add(77, "Megaman");
    public final static CardSeries HaruhiSuzumiya = Add(78, "HaruhiSuzumiya");
    public final static CardSeries Hyouka = Add(79, "Hyouka");
    public final static CardSeries Aria = Add(80, "Aria");
    public final static CardSeries LaidBackCamp = Add(81, "LaidBackCamp");
    public final static CardSeries Air = Add(82, "Air");
    public final static CardSeries VioletEvergarden = Add(83, "VioletEvergarden");
    public final static CardSeries Monogatari = Add(84, "Monogatari");
    public final static CardSeries Persona = Add(85, "Persona");

    public BetaCardSeries(int id, String name)
    {
        super(id, name);
    }

    public boolean Equals(CardSeries other)
    {
        return other != null && ID == other.ID;
    }

    public static void InitializeStrings()
    {
        for (Integer k : betaIDs.keySet())
        {
            CardSeries s = betaIDs.get(k);
            s.LocalizedName = BetaResources.Beta.Strings.Series.SeriesName(k);
        }
    }

    private static CardSeries Add(int id, String name)
    {
        final CardSeries s = new CardSeries(id, name);
        if (id > 0)
        {
            betaIDs.put(id, s);
            _mapIDs.Get(null).put(id, s);
            _mapNames.Get(null).put(name, s);
        }

        return s;
    }

    @Override
    public String toString()
    {
        return Name != null ? Name : "";
    }
}