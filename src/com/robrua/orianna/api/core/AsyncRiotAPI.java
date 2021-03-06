package com.robrua.orianna.api.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.robrua.orianna.api.dto.BaseRiotAPI;
import com.robrua.orianna.store.DataStore;
import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.api.LoadPolicy;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.common.Season;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.matchhistory.MatchSummary;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.staticdata.Item;
import com.robrua.orianna.type.core.staticdata.MapDetails;
import com.robrua.orianna.type.core.staticdata.Mastery;
import com.robrua.orianna.type.core.staticdata.Realm;
import com.robrua.orianna.type.core.staticdata.Rune;
import com.robrua.orianna.type.core.staticdata.SummonerSpell;
import com.robrua.orianna.type.core.stats.ChampionStats;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.status.Shard;
import com.robrua.orianna.type.core.status.ShardStatus;
import com.robrua.orianna.type.core.summoner.MasteryPage;
import com.robrua.orianna.type.core.summoner.RunePage;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.core.team.Team;
import com.robrua.orianna.type.exception.APIException;

/**
 * Accesses the <a href="http://developer.riotgames.com/api/methods">LoL REST
 * API</a> asynchronously and provides results in easy-to-use Java objects.
 *
 * @author Rob Rua (FatalElement - NA) (robrua@alumni.cmu.edu)
 */
public abstract class AsyncRiotAPI {
    /**
     * @param action
     *            what to do with the challenger league
     * @param queueType
     *            the ranked queue type to get the challenger league of
     */
    public static void getChallenger(final Action<League> action, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getChallenger(queueType));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion
     * @param ID
     *            the ID of the champion to get
     */
    public static void getChampion(final Action<Champion> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getChampion(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion
     * @param name
     *            the name of the champion to get
     */
    public static void getChampion(final Action<Champion> action, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getChampion(name));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all the champions
     */
    public static void getChampions(final Action<List<Champion>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getChampions());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champions
     * @param IDs
     *            the IDs of the champions to get
     */
    public static void getChampions(final Action<List<Champion>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getChampions(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champions
     * @param IDs
     *            the IDs of the champions to get
     */
    public static void getChampions(final Action<List<Champion>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getChampions(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion's status
     * @param champion
     *            the champion to get status for
     */
    public static void getChampionStatus(final Action<ChampionStatus> action, final Champion champion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(ChampionAPI.getChampionStatus(champion));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champion's status
     * @param ID
     *            the ID of the champion to get status for
     */
    public static void getChampionStatus(final Action<ChampionStatus> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(ChampionAPI.getChampionStatus(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champions' statuses
     * @param IDs
     *            the IDs of the champions to get statuses for
     */
    public static void getChampionStatuses(final Action<List<ChampionStatus>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(ChampionAPI.getChampionStatuses(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the champions' statuses
     * @param IDs
     *            the IDs of the champions to get statuses for
     */
    public static void getChampionStatuses(final Action<List<ChampionStatus>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(ChampionAPI.getChampionStatuses(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all champion's statuses
     */
    public static void getChampionStatuses(final Action<Map<Champion, ChampionStatus>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(ChampionAPI.getChampionStatuses());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all (possibly only free to play) champion's
     *            statuses
     * @param freeToPlay
     *            whether to only get free to play champions
     */
    public static void getChampionStatuses(final Action<Map<Champion, ChampionStatus>> action, final boolean freeToPlay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(ChampionAPI.getChampionStatuses(freeToPlay));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's current game or null if they
     *            aren't in one
     * @param summonerID
     *            the ID of the summoner to get current game for
     */
    public static void getCurrentGame(final Action<CurrentGame> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(CurrentGameAPI.getCurrentGame(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's current game or null if they
     *            aren't in one
     * @param summonerName
     *            the name of the summoner to get current game for
     */
    public static void getCurrentGame(final Action<CurrentGame> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(CurrentGameAPI.getCurrentGame(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's current game or null if they
     *            aren't in one
     * @param summoner
     *            the summoner to get current game for
     */
    public static void getCurrentGame(final Action<CurrentGame> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(CurrentGameAPI.getCurrentGame(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the current featured games
     */
    public static void getFeaturedGames(final Action<List<CurrentGame>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(FeaturedGamesAPI.getFeaturedGames());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the item
     * @param ID
     *            the ID of the item to get
     */
    public static void getItem(final Action<Item> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getItem(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all the items
     */
    public static void getItems(final Action<List<Item>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getItems());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the items
     * @param IDs
     *            the IDs of the items to get
     */
    public static void getItems(final Action<List<Item>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getItems(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the items
     * @param IDs
     *            the IDs of the items to get
     */
    public static void getItems(final Action<List<Item>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getItems(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the languages
     */
    public static void getLanguages(final Action<List<String>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getLanguages());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the language strings
     */
    public static void getLanguageStrings(final Action<Map<String, String>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getLanguageStrings());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's league entries
     * @param summoner
     *            the summoner to get league entries for
     */
    public static void getLeagueEntriesBySummoner(final Action<List<League>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummoner(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summoners
     *            the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummoner(final Action<List<List<League>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummoner(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summoners
     *            the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummoner(final Action<List<List<League>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummoner(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's league entries
     * @param summonerID
     *            the ID of the summoner to get league entries for
     */
    public static void getLeagueEntriesBySummonerID(final Action<List<League>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummonerID(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summonerIDs
     *            the IDs of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerID(final Action<List<List<League>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summonerIDs
     *            the IDs of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerID(final Action<List<List<League>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummonerID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's league entries
     * @param summonerName
     *            the name of the summoner to get league entries for
     */
    public static void getLeagueEntriesBySummonerName(final Action<List<League>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummonerName(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summonerNames
     *            the names of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerName(final Action<List<List<League>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummonerName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' league entries
     * @param summonerNames
     *            the names of the summoners to get league entries for
     */
    public static void getLeagueEntriesBySummonerName(final Action<List<List<League>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesBySummonerName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's league entries
     * @param team
     *            the team to get league entries for
     */
    public static void getLeagueEntriesByTeam(final Action<List<League>> action, final Team team) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesByTeam(team));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' league entries
     * @param teams
     *            the teams to get league entries for
     */
    public static void getLeagueEntriesByTeam(final Action<List<List<League>>> action, final List<Team> teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesByTeam(teams));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' league entries
     * @param teams
     *            the teams to get league entries for
     */
    public static void getLeagueEntriesByTeam(final Action<List<List<League>>> action, final Team... teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesByTeam(teams));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's league entries
     * @param teamID
     *            the ID of the team to get league entries for
     */
    public static void getLeagueEntriesByTeamID(final Action<List<League>> action, final String teamID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesByTeamID(teamID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' league entries
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     */
    public static void getLeagueEntriesByTeamID(final Action<List<List<League>>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesByTeamID(teamIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' league entries
     * @param teamIDs
     *            the IDs of the teams to get league entries for
     */
    public static void getLeagueEntriesByTeamID(final Action<List<List<League>>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeagueEntriesByTeamID(teamIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's leagues
     * @param summoner
     *            the summoner to get leagues for
     */
    public static void getLeaguesBySummoner(final Action<List<League>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummoner(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' leagues
     * @param summoners
     *            the summoners to get leagues for
     */
    public static void getLeaguesBySummoner(final Action<List<List<League>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummoner(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' leagues
     * @param summoners
     *            the summoners to get leagues for
     */
    public static void getLeaguesBySummoner(final Action<List<List<League>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummoner(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's leagues
     * @param summonerID
     *            the ID of the summoner to get leagues for
     */
    public static void getLeaguesBySummonerID(final Action<List<League>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummonerID(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' leagues
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerID(final Action<List<List<League>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummonerID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' leagues
     * @param summonerIDs
     *            the IDs of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerID(final Action<List<List<League>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummonerID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's leagues
     * @param summonerName
     *            the name of the summoner to get leagues for
     */
    public static void getLeaguesBySummonerName(final Action<List<League>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummonerName(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' leagues
     * @param summonerNames
     *            the names of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerName(final Action<List<List<League>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummonerName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' leagues
     * @param summonerNames
     *            the names of the summoners to get leagues for
     */
    public static void getLeaguesBySummonerName(final Action<List<List<League>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesBySummonerName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's leagues
     * @param team
     *            the team to get leagues for
     */
    public static void getLeaguesByTeam(final Action<List<League>> action, final Team team) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesByTeam(team));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' leagues
     * @param teams
     *            the teams to get leagues for
     */
    public static void getLeaguesByTeam(final Action<List<List<League>>> action, final List<Team> teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesByTeam(teams));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' leagues
     * @param teams
     *            the teams to get leagues for
     */
    public static void getLeaguesByTeam(final Action<List<List<League>>> action, final Team... teams) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesByTeam(teams));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team's leagues
     * @param teamID
     *            the ID of the team to get leagues for
     */
    public static void getLeaguesByTeamID(final Action<List<League>> action, final String teamID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesByTeamID(teamID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' leagues
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     */
    public static void getLeaguesByTeamID(final Action<List<List<League>>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesByTeamID(teamIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams' leagues
     * @param teamIDs
     *            the IDs of the teams to get leagues for
     */
    public static void getLeaguesByTeamID(final Action<List<List<League>>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(LeagueAPI.getLeaguesByTeamID(teamIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with information for the maps
     */
    public static void getMapInformation(final Action<List<MapDetails>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getMapInformation());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all the masteries
     */
    public static void getMasteries(final Action<List<Mastery>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getMasteries());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the masteries
     * @param IDs
     *            the IDs of the masteries to get
     */
    public static void getMasteries(final Action<List<Mastery>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getMasteries(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the masteries
     * @param IDs
     *            the IDs of the masteries to get
     */
    public static void getMasteries(final Action<List<Mastery>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getMasteries(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the mastery
     * @param ID
     *            the ID of the mastery to get
     */
    public static void getMastery(final Action<Mastery> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getMastery(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' mastery pages
     * @param summoners
     *            the summoners to get mastery pages for
     */
    public static void getMasteryPages(final Action<List<List<MasteryPage>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPages(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' mastery pages
     * @param summoners
     *            the summoners to get mastery pages for
     */
    public static void getMasteryPages(final Action<List<List<MasteryPage>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPages(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's mastery pages
     * @param summoner
     *            the summoner to get mastery pages for
     */
    public static void getMasteryPages(final Action<List<MasteryPage>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPages(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' mastery pages
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByID(final Action<List<List<MasteryPage>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPagesByID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' mastery pages
     * @param summonerIDs
     *            the IDs of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByID(final Action<List<List<MasteryPage>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPagesByID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's mastery pages
     * @param summonerID
     *            the ID of the summoner to get mastery pages for
     */
    public static void getMasteryPagesByID(final Action<List<MasteryPage>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPagesByID(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' mastery pages
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByName(final Action<List<List<MasteryPage>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPagesByName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' mastery pages
     * @param summonerNames
     *            the names of the summoners to get mastery pages for
     */
    public static void getMasteryPagesByName(final Action<List<List<MasteryPage>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPagesByName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's mastery pages
     * @param summonerName
     *            the name of the summoner to get mastery pages for
     */
    public static void getMasteryPagesByName(final Action<List<MasteryPage>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getMasteryPagesByName(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the match
     * @param ID
     *            the ID of the match to get
     */
    public static void getMatch(final Action<Match> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchAPI.getMatch(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summonerID
     *            the summoner to get match history for
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final int beginIndex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, beginIndex));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final int beginIndex, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, championIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final int beginIndex, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, queueType));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final int beginIndex, final QueueType queueType,
            final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, beginIndex, queueType, championIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, championIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, queueType));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summonerID
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param championIDs
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final long summonerID, final QueueType queueType, final List<Long> championIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summonerID, queueType, championIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summoner
     *            the summoner to get match history for
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final int beginIndex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, beginIndex));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param champions
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final int beginIndex, final List<Champion> champions) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, beginIndex, champions));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final int beginIndex, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, beginIndex, queueType));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches after beginIndex
     *            for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param beginIndex
     *            the game index to start from
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param champions
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final int beginIndex, final QueueType queueType,
            final List<Champion> champions) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, beginIndex, queueType, champions));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param champions
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final List<Champion> champions) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, champions));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final QueueType queueType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, queueType));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the 15 most recent matches for the summoner
     * @param summoner
     *            the summoner to get match history for
     * @param queueType
     *            the queue type to limit games to (only ranked queues)
     * @param champions
     *            the champions to limit games to
     */
    public static void getMatchHistory(final Action<List<MatchSummary>> action, final Summoner summoner, final QueueType queueType,
            final List<Champion> champions) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(MatchHistoryAPI.getMatchHistory(summoner, queueType, champions));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summonerID
     *            the summoner to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getRankedStats(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summonerID
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final long summonerID, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getRankedStats(summonerID, season));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summoner
     *            the summoner to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getRankedStats(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the ranked stats for the summoner by champion
     *            (null entry contains totals over all champions)
     * @param summoner
     *            the summoner to get ranked stats for
     * @param season
     *            the season to get ranked stats for
     */
    public static void getRankedStats(final Action<Map<Champion, ChampionStats>> action, final Summoner summoner, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getRankedStats(summoner, season));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the realm
     */
    public static void getRealm(final Action<Realm> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getRealm());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's recent games
     * @param summonerID
     *            the ID of the summoner to get recent games for
     */
    public static void getRecentGames(final Action<List<Game>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(GameAPI.getRecentGames(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's recent games
     * @param summonerName
     *            the name of the summoner to get recent games for
     */
    public static void getRecentGames(final Action<List<Game>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(GameAPI.getRecentGames(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's recent games
     * @param summoner
     *            the summoner to get recent games for
     */
    public static void getRecentGames(final Action<List<Game>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(GameAPI.getRecentGames(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the rune
     * @param ID
     *            the ID of the rune to get
     */
    public static void getRune(final Action<Rune> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getRune(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' rune pages
     * @param summoners
     *            the summoners to get rune pages for
     */
    public static void getRunePages(final Action<List<List<RunePage>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePages(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' rune pages
     * @param summoners
     *            the summoners to get rune pages for
     */
    public static void getRunePages(final Action<List<List<RunePage>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePages(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's rune pages
     * @param summoner
     *            the summoner to get rune pages for
     */
    public static void getRunePages(final Action<List<RunePage>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePages(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' rune pages
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     */
    public static void getRunePagesByID(final Action<List<List<RunePage>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePagesByID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' rune pages
     * @param summonerIDs
     *            the IDs of the summoners to get rune pages for
     */
    public static void getRunePagesByID(final Action<List<List<RunePage>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePagesByID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's rune pages
     * @param summonerID
     *            the ID of the summoner to get rune pages for
     */
    public static void getRunePagesByID(final Action<List<RunePage>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePagesByID(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' rune pages
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     */
    public static void getRunePagesByName(final Action<List<List<RunePage>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePagesByName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' rune pages
     * @param summonerNames
     *            the names of the summoners to get rune pages for
     */
    public static void getRunePagesByName(final Action<List<List<RunePage>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePagesByName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's rune pages
     * @param summonerName
     *            the name of the summoner to get rune pages for
     */
    public static void getRunePagesByName(final Action<List<RunePage>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getRunePagesByName(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all the runes
     */
    public static void getRunes(final Action<List<Rune>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getRunes());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the runes
     * @param IDs
     *            the IDs of the runes to get
     */
    public static void getRunes(final Action<List<Rune>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getRunes(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the runes
     * @param IDs
     *            the IDs of the runes to get
     */
    public static void getRunes(final Action<List<Rune>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getRunes(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the status shard for that region
     * @param region
     *            the region to get the shard for
     */
    public static void getShard(final Action<ShardStatus> action, final Region region) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatusAPI.getShard(region));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the status shard list
     */
    public static void getShards(final Action<List<Shard>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatusAPI.getShards());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the stats for the summoner by queue type
     * @param summonerID
     *            the summoner to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getStats(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the stats for the summoner by queue type
     * @param summonerID
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final long summonerID, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getStats(summonerID, season));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the stats for the summoner by queue type
     * @param summoner
     *            the summoner to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getStats(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the stats for the summoner by queue type
     * @param summoner
     *            the summoner to get stats for
     * @param season
     *            the season to get stats for
     */
    public static void getStats(final Action<Map<PlayerStatsSummaryType, PlayerStatsSummary>> action, final Summoner summoner, final Season season) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StatsAPI.getStats(summoner, season));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner
     * @param ID
     *            the ID of the summoner to get
     */
    public static void getSummonerByID(final Action<Summoner> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonerByID(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param name
     *            the name of the summoner to get
     */
    public static void getSummonerByName(final Action<Summoner> action, final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonerByName(name));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's name
     * @param ID
     *            the ID of the summoner to get the names of
     */
    public static void getSummonerName(final Action<String> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonerName(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param IDs
     *            the IDs of the summoners to get
     */
    public static void getSummonersByID(final Action<List<Summoner>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonersByID(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param IDs
     *            the IDs of the summoners to get
     */
    public static void getSummonersByID(final Action<List<Summoner>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonersByID(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param names
     *            the names of the summoners to get
     */
    public static void getSummonersByName(final Action<List<Summoner>> action, final List<String> names) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonersByName(names));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners
     * @param names
     *            the names of the summoners to get
     */
    public static void getSummonersByName(final Action<List<Summoner>> action, final String... names) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonersByName(names));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' names
     * @param IDs
     *            the IDs of the summoners to get the names of
     */
    public static void getSummonersNames(final Action<List<String>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonersNames(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' names
     * @param IDs
     *            the IDs of the summoners to get the names of
     */
    public static void getSummonersNames(final Action<List<String>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(SummonerAPI.getSummonersNames(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner spell
     * @param ID
     *            the ID of the summoner spell to get
     */
    public static void getSummonerSpell(final Action<SummonerSpell> action, final long ID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getSummonerSpell(ID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with all the summoner spells
     */
    public static void getSummonerSpells(final Action<List<SummonerSpell>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getSummonerSpells());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner spells
     * @param IDs
     *            the IDs of the summoner spells to get
     */
    public static void getSummonerSpells(final Action<List<SummonerSpell>> action, final List<Long> IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getSummonerSpells(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner spells
     * @param IDs
     *            the IDs of the summoner spells to get
     */
    public static void getSummonerSpells(final Action<List<SummonerSpell>> action, final long... IDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getSummonerSpells(IDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the team
     * @param teamID
     *            the ID of the team to get
     */
    public static void getTeam(final Action<Team> action, final String teamID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeam(teamID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams
     * @param teamIDs
     *            the IDs of the teams to get
     */
    public static void getTeams(final Action<List<Team>> action, final List<String> teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeams(teamIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the teams
     * @param teamIDs
     *            the IDs of the teams to get
     */
    public static void getTeams(final Action<List<Team>> action, final String... teamIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeams(teamIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summoners
     *            the summoners to get teams for
     */
    public static void getTeamsBySummoner(final Action<List<List<Team>>> action, final List<Summoner> summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummoner(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summoners
     *            the summoners to get teams for
     */
    public static void getTeamsBySummoner(final Action<List<List<Team>>> action, final Summoner... summoners) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummoner(summoners));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summoner
     *            the summoner to get teams for
     */
    public static void getTeamsBySummoner(final Action<List<Team>> action, final Summoner summoner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummoner(summoner));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     */
    public static void getTeamsBySummonerID(final Action<List<List<Team>>> action, final List<Long> summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummonerID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summonerIDs
     *            the IDs of the summoners to get teams for
     */
    public static void getTeamsBySummonerID(final Action<List<List<Team>>> action, final long... summonerIDs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummonerID(summonerIDs));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's teams
     * @param summonerID
     *            the ID of the summoner to get teams for
     */
    public static void getTeamsBySummonerID(final Action<List<Team>> action, final long summonerID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummonerID(summonerID));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summonerNames
     *            the names of the summoners to get teams for
     */
    public static void getTeamsBySummonerName(final Action<List<List<Team>>> action, final List<String> summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummonerName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoners' teams
     * @param summonerNames
     *            the names of the summoners to get teams for
     */
    public static void getTeamsBySummonerName(final Action<List<List<Team>>> action, final String... summonerNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummonerName(summonerNames));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the summoner's teams
     * @param summonerName
     *            the names of the summoner to get teams for
     */
    public static void getTeamsBySummonerName(final Action<List<List<Team>>> action, final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(TeamAPI.getTeamsBySummonerName(summonerName));
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * @param action
     *            what to do with the versions
     */
    public static void getVersions(final Action<List<String>> action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.perform(StaticDataAPI.getVersions());
                }
                catch(final APIException e) {
                    action.handle(e);
                }
            }
        }).start();
    }

    /**
     * If turned on, prints the URI of calls made to stdout
     *
     * @param on
     *            whether to print the URI of calls to stdout
     */
    public static void printCalls(final boolean on) {
        BaseRiotAPI.printCalls(on);
    }

    /**
     * Removes any set proxy
     */
    public static void removeProxy() {
        BaseRiotAPI.removeProxy();
    }

    /**
     * Sets the API Key to use for queries
     *
     * @param newAPIKey
     *            the API Key to use for queries
     */
    public static void setAPIKey(final String newAPIKey) {
        BaseRiotAPI.setAPIKey(newAPIKey);
    }

    /**
     * Sets the data store to use for caching
     *
     * @param newStore
     *            the data store to use for caching
     */
    public static void setDataStore(final DataStore newStore) {
        RiotAPI.setDataStore(newStore);
    }

    /**
     * Sets the load policy for the API
     *
     * @param policy
     *            the new load policy
     */
    public static void setLoadPolicy(final LoadPolicy policy) {
        RiotAPI.setLoadPolicy(policy);
    }

    /**
     * Sets the server mirror to hit for queries
     *
     * @param newMirror
     *            the server mirror to hit for queries
     */
    public static void setMirror(final Region newMirror) {
        BaseRiotAPI.setMirror(newMirror);
    }

    /**
     * Sets the proxy to access the API through
     *
     * @param IP
     *            the IP address of the proxy server
     * @param port
     *            the working port for the proxy server
     */
    public static void setProxy(final String IP, final int port) {
        BaseRiotAPI.setProxy(IP, port);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final Collection<RateLimit> limits) {
        BaseRiotAPI.setRateLimit(limits);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param callsPerEpoch
     *            the number of calls allowed in each epoch
     * @param secondsPerEpoch
     *            the number of seconds in each epoch
     */
    public static void setRateLimit(final int callsPerEpoch, final int secondsPerEpoch) {
        BaseRiotAPI.setRateLimit(callsPerEpoch, secondsPerEpoch);
    }

    /**
     * Sets a new rate limit for the API, removing any old ones
     *
     * @param limit
     *            the new rate limit
     */
    public static void setRateLimit(final RateLimit limit) {
        BaseRiotAPI.setRateLimit(limit);
    }

    /**
     * Sets multiple new rate limits for the API, removing any old ones
     *
     * @param limits
     *            the rate limits to apply
     */
    public static void setRateLimit(final RateLimit... limits) {
        BaseRiotAPI.setRateLimit(limits);
    }

    /**
     * Sets the region to get information from the API for
     *
     * @param newRegion
     *            the region to get information from the API for
     */
    public static void setRegion(final Region newRegion) {
        BaseRiotAPI.setRegion(newRegion);
    }
}
