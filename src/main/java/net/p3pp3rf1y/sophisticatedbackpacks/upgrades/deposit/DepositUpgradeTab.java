package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.BackpackScreen;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.Dimension;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.Position;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.FilterLogicControl;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.FilteredUpgradeContainer;

import static net.p3pp3rf1y.sophisticatedbackpacks.client.gui.TranslationHelper.translUpgrade;
import static net.p3pp3rf1y.sophisticatedbackpacks.client.gui.TranslationHelper.translUpgradeTooltip;

@OnlyIn(Dist.CLIENT)
public class DepositUpgradeTab extends UpgradeSettingsTab<FilteredUpgradeContainer<DepositUpgradeWrapper>> {
	protected FilterLogicControl filterLogicControl;

	protected DepositUpgradeTab(FilteredUpgradeContainer<DepositUpgradeWrapper> upgradeContainer, Position position, Dimension openTabDimension, BackpackScreen screen, ITextComponent tabLabel, ITextComponent closedTooltip) {
		super(upgradeContainer, position, openTabDimension, screen, tabLabel, closedTooltip);
	}

	@Override
	protected void moveSlotsToTab() {
		filterLogicControl.moveSlotsToView(screen.getGuiLeft(), screen.getGuiTop());
	}

	public static class Basic extends DepositUpgradeTab {
		public Basic(FilteredUpgradeContainer<DepositUpgradeWrapper> upgradeContainer, Position position, BackpackScreen screen) {
			super(upgradeContainer, position, new Dimension(63, 106), screen,
					new TranslationTextComponent(translUpgrade("deposit")), new TranslationTextComponent(translUpgradeTooltip("deposit")));
			filterLogicControl = addHideableChild(new FilterLogicControl.Basic(new Position(x + 3, y + 24), getContainer(), 3));
		}
	}

	public static class Advanced extends DepositUpgradeTab {
		public Advanced(FilteredUpgradeContainer<DepositUpgradeWrapper> upgradeContainer, Position position, BackpackScreen screen) {
			super(upgradeContainer, position, new Dimension(81, 124), screen,
					new TranslationTextComponent(translUpgrade("advanced_deposit")), new TranslationTextComponent(translUpgradeTooltip("advanced_deposit")));
			filterLogicControl = addHideableChild(new FilterLogicControl.Advanced(new Position(x + 3, y + 24), getContainer(), 4));
		}
	}
}
