/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import persistentie.MonsterMapper;
import persistentie.TreasureMapper;

/**
 *
 * @author Robin
 */
public class DomeinController {

    private final MonsterMapper monsterMapper;
    private final TreasureMapper treasureMapper;

    /**
     * Constructor for DomeinController, initializes both Mappers
     */
    public DomeinController() {
        monsterMapper = new MonsterMapper();
        treasureMapper = new TreasureMapper();
    }

    /**
     *
     * @param treasure the treasure to be added by using treasureMapper
     * @return true if treasure was added succesfully
     */
    public boolean addTreasure(Treasure treasure) {
        return treasureMapper.addTreasure(treasure);
    }

    /**
     *
     * @param monster the monster to be added by using monsterMapper
     * @return true if Monster was added succesfully
     */
    public boolean addMonster(Monster monster) {
        return monsterMapper.addMonster(monster);
    }

    /**
     *
     * @param treasure the treasture to be deleted by using treasureMapper
     * @return true if treasure was deleted succesfully
     */
    public boolean deleteTreasure(Treasure treasure) {
        return treasureMapper.deleteTreasure(treasure);
    }

    /**
     *
     * @param id the id of the treasure to be deleted by using treasureMapper
     * @return true if treasure was deleted succesfully
     */
    public boolean deleteTreasure(int id) {
        return treasureMapper.deleteTreasure(id);
    }

    /**
     *
     * @param monster the monster to be deleted by using monsterMapper
     * @return true if monster was deleted succesfully
     */
    public boolean deleteMonster(Monster monster) {
        return monsterMapper.deleteMonster(monster);
    }

    /**
     *
     * @param id the id of the monster to be deleted by using monsterMapper
     * @return true if monster was deleted succesfully
     */
    public boolean deleteMonster(int id) {
        return monsterMapper.deleteMonster(id);
    }

    /**
     *
     * @param monster the monster to be updated by using monsterMapper
     * @return true if monster was succesfully updated
     */
    public boolean updateMonster(Monster monster) {
        return monsterMapper.updateMonster(monster);
    }

    /**
     *
     * @param treasure the treasure to be updated by using treasureMapper
     * @return true if treasure was succesfully updated
     */
    public boolean updateTreasure(Treasure treasure) {
        return treasureMapper.updateTreasure(treasure);
    }

    /**
     *
     * @return a list with all Monsters in database by using monsterMapper
     */
    public List<Monster> searchAllMonsters() {
        return monsterMapper.searchAllMonsters();
    }

    /**
     *
     * @return a list with all Treasures in database by using treasureMapper
     */
    public List<Treasure> searchAllTreasures() {
        return treasureMapper.searchAllTreasures();
    }

    /**
     *
     * @param monster
     * @param treasure
     * @return
     */
    public boolean addTreasureToMonster(Monster monster, Treasure treasure) {
        return monsterMapper.addTreasureToMonster(monster, treasure);
    }

    /**
     *
     * @param monster
     * @param treasure
     * @return
     */
    public boolean removeTreasureFromMonster(Monster monster, Treasure treasure) {
        return monsterMapper.removeTreasureFromMonster(monster, treasure);
    }

    /**
     *
     * @param id the id of the monster you want to get the treasures from
     * @return a list of all the treasures a monster guards by using
     * monsterMapper
     */
    public List<Treasure> searchAllTreasuresFromMonster(int id) {
        return monsterMapper.searchAllTreasuresFromMonster(id);
    }

    /**
     *
     * @param monster the monster you want to get the treasures from
     * @return a list of all the treasures a monster guards by using
     * monsterMapper
     */
    public List<Treasure> searchAllTreasureFromMonster(Monster monster) {
        return monsterMapper.searchAllTreasuresFromMonster(monster);
    }

    /**
     *
     * @param id the id of the treasure you want to check if it's linked with a
     * monster using isUnconnected-method from TreasureMapper
     * @return 1 if unconnected, 0 otherwise or -1 in case an unexpected error
     * occurred
     *
     */
    public int isUnconnectedTreasure(int id) {
        return treasureMapper.isUnconnected(id);
    }

    public Boolean addTreasureToMonster(int monsterId, int treasureId) {
        return monsterMapper.addTreasureToMonster(monsterId, treasureId);
    }

    public Boolean removeTreasureFromMonster(int monsterId, int treasureId) {
        return monsterMapper.removeTreasureFromMonster(monsterId, treasureId);
    }
}
