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
     * Constructor for DomeinController, initializes monsterMapper and
     * treasureMapper
     *
     * @see persistentie.MonsterMapper
     * @see persistentie.TreasureMapper
     *
     */
    public DomeinController() {
        monsterMapper = new MonsterMapper();
        treasureMapper = new TreasureMapper();
    }

    /**
     * Add a treasure to the database
     *
     * @param treasure the treasure to be added by using {@link persistentie.TreasureMapper#addTreasure(Treasure) addTreasure-method
     * } from treasureMapper
     * @return true if treasure was added succesfully
     */
    public boolean addTreasure(Treasure treasure) {
        return treasureMapper.addTreasure(treasure);
    }

    /**
     * Add a monster to the database
     *
     * @param monster the monster to be added by using {@link persistentie.MonsterMapper#addMonster(Monster) addMonster-method
     * } from monsterMapper
     * @return true if Monster was added succesfully
     */
    public boolean addMonster(Monster monster) {
        return monsterMapper.addMonster(monster);
    }

    /**
     * Delete a treasure in the database
     *
     * @param treasure the treasture to be deleted by using
     * {@link persistentie.TreasureMapper#deleteTreasure(Treasure) deleteTreasure-method}
     * from treasureMapper
     * @return true if treasure was deleted succesfully
     */
    public boolean deleteTreasure(Treasure treasure) {
        return treasureMapper.deleteTreasure(treasure);
    }

    /**
     * Delete a treasure in the database
     *
     * @param id the id of the treasure to be deleted by using
     * {@link persistentie.TreasureMapper#deleteTreasure(int) deleteTreasure-method}
     * from treasureMapper
     * @return true if treasure was deleted succesfully
     */
    public boolean deleteTreasure(int id) {
        return treasureMapper.deleteTreasure(id);
    }

    /**
     * Delete a monster in the database
     *
     * @param monster the monster to be deleted by using {@link persistentie.MonsterMapper#deleteMonster(Monster) deleteMonster-method
     * } from monsterMapper
     * @return true if monster was deleted succesfully
     */
    public boolean deleteMonster(Monster monster) {
        return monsterMapper.deleteMonster(monster);
    }

    /**
     * Delete a monster in the database
     *
     * @param id the id of the monster to be deleted by using {@link persistentie.MonsterMapper#deleteMonster(int) deleteMonster-method
     * } from monsterMapper
     * @return true if monster was deleted succesfully
     */
    public boolean deleteMonster(int id) {
        return monsterMapper.deleteMonster(id);
    }

    /**
     * Update a monster in the database
     *
     * @param monster the monster to be updated by using {@link persistentie.MonsterMapper#updateMonster(Monster) updateMonster-method
     * } from monsterMapper
     * @return true if monster was succesfully updated
     */
    public boolean updateMonster(Monster monster) {
        return monsterMapper.updateMonster(monster);
    }

    /**
     * Update a treasure in the database
     *
     * @param treasure the treasure to be updated by using
     * {@link persistentie.TreasureMapper#updateTreasure(Treasure) updateTreasure-method}
     * from treasureMapper
     * @return true if treasure was succesfully updated
     */
    public boolean updateTreasure(Treasure treasure) {
        return treasureMapper.updateTreasure(treasure);
    }

    /**
     * Give a list of all monsters in the database
     *
     * @return a list with all Monsters in database by using {@link persistentie.MonsterMapper#searchAllMonsters() searchAllMonsters-method
     * } from monsterMapper
     */
    public List<Monster> searchAllMonsters() {
        return monsterMapper.searchAllMonsters();
    }

    /**
     * Give a list of all treasures in the database
     *
     * @return a list with all Treasures in database by using
     * {@link persistentie.TreasureMapper#searchAllTreasures() searchAllTreasures()}
     * from treasureMapper
     */
    public List<Treasure> searchAllTreasures() {
        return treasureMapper.searchAllTreasures();
    }

    /**
     * Add a treasure to a monster using {@link persistentie.MonsterMapper#addTreasureToMonster(Monster,Treasure) addTreasureToMonster-method
     * } from monsterMapper
     *
     * @param monster the monster to which we'll try to link a treasure
     * @param treasure the treasure that will be linked to the monster
     * @return true if treasure was succesfully linked to the monster, false if
     * not
     */
    public boolean addTreasureToMonster(Monster monster, Treasure treasure) {
        return monsterMapper.addTreasureToMonster(monster, treasure);
    }

    /**
     * Remove a treasure from a monster using {@link persistentie.MonsterMapper#removeTreasureFromMonster(Monster,Treasure) removeTreasureFromMonster-method
     * } from monsterMapper
     *
     * @param monster the monster from which we'll try to remove a treasure
     * @param treasure the treasure that will be removed from the monster
     * @return true if treasure was succesfully removed from the monster, false
     * if not
     */
    public boolean removeTreasureFromMonster(Monster monster, Treasure treasure) {
        return monsterMapper.removeTreasureFromMonster(monster, treasure);
    }

    /**
     * Give a list of all treasures connected to a monster
     *
     * @param id the id of the monster you want to get the treasures from
     * @return a list of all the treasures a monster guards by using
     * {@link persistentie.MonsterMapper#searchAllTreasuresFromMonster(int) removeTreasureFromMonster-method }
     * from monsterMapper
     */
    public List<Treasure> searchAllTreasuresFromMonster(int id) {
        return monsterMapper.searchAllTreasuresFromMonster(id);
    }

    /**
     * Give a list of all treasures connected to a monster
     *
     * @param monster the monster you want to get the treasures from
     * @return a list of all the treasures a monster guards by using
     * {@link persistentie.MonsterMapper#searchAllTreasuresFromMonster(Monster) searchAllTreasuresFromMonster-method }
     * from monsterMapper
     */
    public List<Treasure> searchAllTreasuresFromMonster(Monster monster) {
        return monsterMapper.searchAllTreasuresFromMonster(monster);
    }

    /**
     * Check connection of treasure with monster(s)
     *
     * @param id the id of the treasure of which you want to check if it's
     * linked with a monster using
     * {@link persistentie.TreasureMapper#isUnconnected(int) isUnconnected-method}
     * from TreasureMapper
     * @return 1 if unconnected, 0 otherwise or -1 in case an unexpected error
     * occurred
     *
     */
    public int isUnconnectedTreasure(int id) {
        return treasureMapper.isUnconnected(id);
    }

    /**
     * Check connection of monster with treasure(s)
     *
     * @param id the id of the monster of which you want to check if is still
     * has treasures linked to it using the {@link persistentie.MonsterMapper#isUnconnected(int) isUnconnected-method
     * } from monsterMapper
     * @return 1 if unconnected, 0 otherwise or -1 in case an unexpected error
     * occurred
     *
     */
    public int isUnconnectedMonster(int id) {
        return monsterMapper.isUnconnected(id);
    }

    /**
     * Add a treasure to a monster by using {@link persistentie.MonsterMapper#addTreasureToMonster(int,int) addTreasureToMonster-method
     * } from monsterMapper
     *
     * @param monsterId the id of the monster that will be added a treasure to
     * @param treasureId the id of the treasure that will be added to the
     * monster
     *
     * @return true if linking was succesfull, false if not
     *
     */
    public Boolean addTreasureToMonster(int monsterId, int treasureId) {
        return monsterMapper.addTreasureToMonster(monsterId, treasureId);
    }

    /**
     * Remove a treasure from a monster by using {@link persistentie.MonsterMapper#removeTreasureFromMonster(int,int) removeTreasureFromMonster-method
     * } from monsterMapper
     *
     * @param monsterId the id of the monster of which a treasure will be
     * removed from
     * @param treasureId the id of the treasure that will be removed from the
     * monster
     *
     * @return true if the link was succesfully broken, false if not
     *
     */
    public Boolean removeTreasureFromMonster(int monsterId, int treasureId) {
        return monsterMapper.removeTreasureFromMonster(monsterId, treasureId);
    }

    /**
     * Check if treasure already exists in the database
     *
     * @param treasure the treasure whose presence in the database has to be
     * checked
     * @return true is treasure doesn't yet exist in the database, false if it
     * does
     */
    public Boolean isNewTreasure(Treasure treasure) {
        List<Treasure> treasures = treasureMapper.searchAllTreasures();

        for (Treasure t : treasures) {
            if (t.equals(treasure)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if monster already exists in the database
     *
     * @param monster the monster whose presence in the database has to be
     * checked
     * @return true is monster doesn't yet exist in the database, false if it
     * does
     */
    public Boolean isNewMonster(Monster monster) {
        List<Monster> monsters = monsterMapper.searchAllMonsters();

        for (Monster m : monsters) {
            if (m.equals(monster)) {
                return false;
            }
        }
        return true;
    }
}
