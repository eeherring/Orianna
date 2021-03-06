package com.robrua.orianna.api.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robrua.orianna.api.Utils;
import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.core.staticdata.MapDetails;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.core.staticdata.Realm;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.dto.staticdata.ChampionList;
import com.robrua.orianna.type.dto.staticdata.ItemList;
import com.robrua.orianna.type.dto.staticdata.MapData;
import com.robrua.orianna.type.dto.staticdata.MasteryList;
import com.robrua.orianna.type.dto.staticdata.RuneList;
import com.robrua.orianna.type.dto.staticdata.SummonerSpellList;

public abstract class StaticDataAPI {
    private static Set<Long> IGNORE_ITEMS = new HashSet<>(Arrays.asList(new Long[] {0L, 3210L, 3207L, 3176L, 3005L, 3131L, 2040L, 2039L, 3186L, 2037L, 3128L,
            3188L}));

    /**
     * @param ID
     *            the ID of the champion to get
     * @return the champion
     */
    public synchronized static Champion getChampion(final long ID) {
        Champion champion = RiotAPI.store.get(Champion.class, ID);
        if(champion != null) {
            return champion;
        }

        final com.robrua.orianna.type.dto.staticdata.Champion champ = BaseRiotAPI.getChampion(ID);
        if(champ == null) {
            return null;
        }
        champion = new Champion(champ);

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(champ.getItemIDs()));
        }

        RiotAPI.store.store(champion, ID);

        return champion;
    }

    /**
     * @param name
     *            the name of the champion to get
     * @return the champion
     */
    public static Champion getChampion(final String name) {
        final List<Champion> champions = getChampions();
        for(final Champion champion : champions) {
            if(champion.getName().equals(name)) {
                return champion;
            }
        }

        return null;
    }

    /**
     * @return all the champions
     */
    public synchronized static List<Champion> getChampions() {
        if(RiotAPI.store.hasAll(Champion.class)) {
            return RiotAPI.store.getAll(Champion.class);
        }

        final ChampionList champs = BaseRiotAPI.getChampions();

        final List<Champion> champions = new ArrayList<>(champs.getData().size());
        final List<Long> IDs = new ArrayList<>(champions.size());
        for(final com.robrua.orianna.type.dto.staticdata.Champion champ : champs.getData().values()) {
            champions.add(new Champion(champ));
            IDs.add(champ.getId().longValue());
        }

        if(RiotAPI.loadPolicy == LoadPolicy.UPFRONT) {
            RiotAPI.getItems(new ArrayList<>(champs.getItemIDs()));
        }

        RiotAPI.store.store(Champion.class, champions, IDs, true);

        return Collections.unmodifiableList(champions);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get
     * @return the champions
     */
    public synchronized static List<Champion> getChampions(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Champion> champions = RiotAPI.store.get(Champion.class, IDs);
        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(champions.get(i) == null) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return champions;
        }

        if(toGet.size() == 1) {
            champions.set(index.get(0), getChampion(toGet.get(0)));
            return champions;
        }

        getChampions();
        final List<Champion> gotten = RiotAPI.store.get(Champion.class, toGet);
        int count = 0;
        for(final Integer id : index) {
            champions.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(champions);
    }

    /**
     * @param IDs
     *            the IDs of the champions to get
     * @return the champions
     */
    public static List<Champion> getChampions(final long... IDs) {
        return getChampions(Utils.convert(IDs));
    }

    /**
     * @param ID
     *            the ID of the item to get
     * @return the item
     */
    public synchronized static Item getItem(final long ID) {
        if(IGNORE_ITEMS.contains(ID)) {
            return null;
        }

        Item item = RiotAPI.store.get(Item.class, ID);
        if(item != null) {
            return item;
        }

        final com.robrua.orianna.type.dto.staticdata.Item it = BaseRiotAPI.getItem(ID);
        item = new Item(it);
        RiotAPI.store.store(item, ID);

        return item;
    }

    /**
     * @return all the items
     */
    public synchronized static List<Item> getItems() {
        if(RiotAPI.store.hasAll(Item.class)) {
            return RiotAPI.store.getAll(Item.class);
        }

        final ItemList its = BaseRiotAPI.getItems();

        final List<Item> items = new ArrayList<>(its.getData().size());
        final List<Long> IDs = new ArrayList<>(items.size());
        for(final com.robrua.orianna.type.dto.staticdata.Item item : its.getData().values()) {
            items.add(new Item(item));
            IDs.add(item.getId().longValue());
        }
        RiotAPI.store.store(Item.class, items, IDs, true);

        return Collections.unmodifiableList(items);
    }

    /**
     * @param IDs
     *            the IDs of the items to get
     * @return the items
     */
    public synchronized static List<Item> getItems(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Item> items = RiotAPI.store.get(Item.class, IDs);
        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(items.get(i) == null && !IGNORE_ITEMS.contains(IDs.get(i))) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return items;
        }

        if(toGet.size() == 1) {
            items.set(index.get(0), getItem(toGet.get(0)));
            return items;
        }

        getItems();
        final List<Item> gotten = RiotAPI.store.get(Item.class, toGet);
        int count = 0;
        for(final Integer id : index) {
            items.add(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(items);
    }

    /**
     * @param IDs
     *            the IDs of the items to get
     * @return the items
     */
    public static List<Item> getItems(final long... IDs) {
        return getItems(Utils.convert(IDs));
    }

    /**
     * @return the languages
     */
    public static List<String> getLanguages() {
        return BaseRiotAPI.getLanguages();
    }

    /**
     * @return the language strings
     */
    public static Map<String, String> getLanguageStrings() {
        final com.robrua.orianna.type.dto.staticdata.LanguageStrings str = BaseRiotAPI.getLanguageStrings();
        return str.getData();
    }

    /**
     * @return information for the maps
     */
    public synchronized static List<MapDetails> getMapInformation() {
        if(RiotAPI.store.hasAll(MapDetails.class)) {
            return RiotAPI.store.getAll(MapDetails.class);
        }

        final MapData inf = BaseRiotAPI.getMapInformation();

        final List<MapDetails> info = new ArrayList<>(inf.getData().size());
        final List<Long> IDs = new ArrayList<>(info.size());
        for(final com.robrua.orianna.type.dto.staticdata.MapDetails map : inf.getData().values()) {
            info.add(new MapDetails(map));
            IDs.add(map.getMapId().longValue());
        }
        RiotAPI.store.store(MapDetails.class, info, IDs, true);

        return Collections.unmodifiableList(info);
    }

    /**
     * @return all the masteries
     */
    public synchronized static List<Mastery> getMasteries() {
        if(RiotAPI.store.hasAll(Mastery.class)) {
            return RiotAPI.store.getAll(Mastery.class);
        }

        final MasteryList its = BaseRiotAPI.getMasteries();

        final List<Mastery> masteries = new ArrayList<>(its.getData().size());
        final List<Long> IDs = new ArrayList<>(masteries.size());
        for(final com.robrua.orianna.type.dto.staticdata.Mastery mastery : its.getData().values()) {
            masteries.add(new Mastery(mastery));
            IDs.add(mastery.getId().longValue());
        }
        RiotAPI.store.store(Mastery.class, masteries, IDs, true);

        return Collections.unmodifiableList(masteries);
    }

    /**
     * @param IDs
     *            the IDs of the masteries to get
     * @return the masteries
     */
    public synchronized static List<Mastery> getMasteries(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Mastery> masteries = RiotAPI.store.get(Mastery.class, IDs);
        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(masteries.get(i) == null) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return masteries;
        }

        if(toGet.size() == 1) {
            masteries.set(index.get(0), getMastery(toGet.get(0)));
            return masteries;
        }

        getMasteries();
        final List<Mastery> gotten = RiotAPI.store.get(Mastery.class, toGet);
        int count = 0;
        for(final Integer id : index) {
            masteries.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(masteries);
    }

    /**
     * @param IDs
     *            the IDs of the masteries to get
     * @return the masteries
     */
    public static List<Mastery> getMasteries(final long... IDs) {
        return getMasteries(Utils.convert(IDs));
    }

    /**
     * @param ID
     *            the ID of the mastery to get
     * @return the mastery
     */
    public synchronized static Mastery getMastery(final long ID) {
        Mastery mastery = RiotAPI.store.get(Mastery.class, ID);
        if(mastery != null) {
            return mastery;
        }

        final com.robrua.orianna.type.dto.staticdata.Mastery mast = BaseRiotAPI.getMastery(ID);
        mastery = new Mastery(mast);
        RiotAPI.store.store(mastery, ID);

        return mastery;
    }

    /**
     * @return the realm
     */
    public synchronized static Realm getRealm() {
        Realm realm = RiotAPI.store.get(Realm.class, "");
        if(realm != null) {
            return realm;
        }

        realm = new Realm(BaseRiotAPI.getRealm());
        RiotAPI.store.store(realm, "");

        return realm;
    }

    /**
     * @param ID
     *            the ID of the rune to get
     * @return the rune
     */
    public synchronized static Rune getRune(final long ID) {
        Rune rune = RiotAPI.store.get(Rune.class, ID);
        if(rune != null) {
            return rune;
        }

        final com.robrua.orianna.type.dto.staticdata.Rune run = BaseRiotAPI.getRune(ID);
        rune = new Rune(run);
        RiotAPI.store.store(rune, ID);

        return rune;
    }

    /**
     * @return all the runes
     */
    public synchronized static List<Rune> getRunes() {
        if(RiotAPI.store.hasAll(Rune.class)) {
            return RiotAPI.store.getAll(Rune.class);
        }

        final RuneList its = BaseRiotAPI.getRunes();

        final List<Rune> runes = new ArrayList<>(its.getData().size());
        final List<Long> IDs = new ArrayList<>(runes.size());
        for(final com.robrua.orianna.type.dto.staticdata.Rune rune : its.getData().values()) {
            runes.add(new Rune(rune));
            IDs.add(rune.getId().longValue());
        }
        RiotAPI.store.store(Rune.class, runes, IDs, true);

        return Collections.unmodifiableList(runes);
    }

    /**
     * @param IDs
     *            the IDs of the runes to get
     * @return the runes
     */
    public synchronized static List<Rune> getRunes(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Rune> runes = RiotAPI.store.get(Rune.class, IDs);
        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(runes.get(i) == null) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return runes;
        }

        if(toGet.size() == 1) {
            runes.set(index.get(0), getRune(toGet.get(0)));
            return runes;
        }

        getRunes();
        final List<Rune> gotten = RiotAPI.store.get(Rune.class, toGet);
        int count = 0;
        for(final Integer id : index) {
            runes.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(runes);
    }

    /**
     * @param IDs
     *            the IDs of the runes to get
     * @return the runes
     */
    public static List<Rune> getRunes(final long... IDs) {
        return getRunes(Utils.convert(IDs));
    }

    /**
     * @param ID
     *            the ID of the summoner spell to get
     * @return the summoner spell
     */
    public synchronized static SummonerSpell getSummonerSpell(final long ID) {
        SummonerSpell spell = RiotAPI.store.get(SummonerSpell.class, ID);
        if(spell != null) {
            return spell;
        }

        final com.robrua.orianna.type.dto.staticdata.SummonerSpell spl = BaseRiotAPI.getSummonerSpell(ID);
        spell = new SummonerSpell(spl);
        RiotAPI.store.store(spell, ID);

        return spell;
    }

    /**
     * @return all the summoner spells
     */
    public synchronized static List<SummonerSpell> getSummonerSpells() {
        if(RiotAPI.store.hasAll(SummonerSpell.class)) {
            return RiotAPI.store.getAll(SummonerSpell.class);
        }

        final SummonerSpellList its = BaseRiotAPI.getSummonerSpells();

        final List<SummonerSpell> spells = new ArrayList<>(its.getData().size());
        final List<Long> IDs = new ArrayList<>(spells.size());
        for(final com.robrua.orianna.type.dto.staticdata.SummonerSpell spell : its.getData().values()) {
            spells.add(new SummonerSpell(spell));
            IDs.add(spell.getId().longValue());
        }
        RiotAPI.store.store(SummonerSpell.class, spells, IDs, true);

        return Collections.unmodifiableList(spells);
    }

    /**
     * @param IDs
     *            the IDs of the summoner spells to get
     * @return the summoner spells
     */
    public synchronized static List<SummonerSpell> getSummonerSpells(final List<Long> IDs) {
        if(IDs.isEmpty()) {
            return Collections.emptyList();
        }

        final List<SummonerSpell> spells = RiotAPI.store.get(SummonerSpell.class, IDs);
        final List<Long> toGet = new ArrayList<>();
        final List<Integer> index = new ArrayList<>();
        for(int i = 0; i < IDs.size(); i++) {
            if(spells.get(i) == null) {
                toGet.add(IDs.get(i));
                index.add(i);
            }
        }

        if(toGet.isEmpty()) {
            return spells;
        }

        if(toGet.size() == 1) {
            spells.set(index.get(0), getSummonerSpell(toGet.get(0)));
            return spells;
        }

        getSummonerSpells();
        final List<SummonerSpell> gotten = RiotAPI.store.get(SummonerSpell.class, toGet);
        int count = 0;
        for(final Integer id : index) {
            spells.set(id, gotten.get(count++));
        }

        return Collections.unmodifiableList(spells);
    }

    /**
     * @param IDs
     *            the IDs of the summoner spells to get
     * @return the summoner spells
     */
    public static List<SummonerSpell> getSummonerSpells(final long... IDs) {
        return getSummonerSpells(Utils.convert(IDs));
    }

    /**
     * @return the versions
     */
    public static List<String> getVersions() {
        return BaseRiotAPI.getVersions();
    }
}
