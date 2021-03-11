package com.brunocollingridge.lovelyllamas.common.entity.ai;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class FollowPlayerGoal extends TargetGoal {
    private final LlamaEntity llama;
    private LivingEntity owner;
    private int field_220802_d;

    public FollowPlayerGoal(LlamaEntity llama) {
        super(llama, false);
        this.llama = llama;
        this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        if (!this.llama.getLeashed()) {
            return false;
        } else if (this.llama.getOwnerUniqueId() != null) {
            PlayerEntity player = this.llama.world.getPlayerByUuid(this.llama.getOwnerUniqueId());
            if (player == null) {
                return false;
            } else {
                this.owner = player.getRevengeTarget();
                int i = player.getRevengeTimer();
                return i != this.field_220802_d && this.isSuitableTarget(this.owner, EntityPredicate.DEFAULT);
            }
        } else return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        if (this.llama.getOwnerUniqueId() != null) {
            this.goalOwner.setAttackTarget(this.owner);
            PlayerEntity player = this.llama.world.getPlayerByUuid(this.llama.getOwnerUniqueId());
            if (player != null) {
                this.field_220802_d = player.getRevengeTimer();
            }

            super.startExecuting();
        }
    }
}