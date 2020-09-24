package skyric.mineInstant.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import skyric.mineInstant.container.AlchemyCaldronContainer;

public class AlchemyCaldronScreen extends ContainerScreen<AlchemyCaldronContainer>{
	
	private static final ResourceLocation ALCHEMY_CALDRON_SCREEN_BACKGROUND 
		= new ResourceLocation("mineinstant", "textures/screen/alchemy_caldron_screen.png");

	public AlchemyCaldronScreen(AlchemyCaldronContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}
	
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
		
		int relMouseX = mouseX - this.guiLeft;
		int relMouseY = mouseY - this.guiTop;
		
		if( container.tileEntity.onRefining() && 137 < relMouseX && relMouseX < 147 && 20 < relMouseY && relMouseY < 30 ) {
			this.renderTooltip(String.format("%.1f s",container.tileEntity.getRefiningProgress()), mouseX, mouseY);
		} else if( container.tileEntity.isBurning() && 37 < relMouseX && relMouseX < 139 && 72 < relMouseY && relMouseY < 77 ) {
			this.renderTooltip(String.format("%.1f s",container.tileEntity.getFuelRemain()), mouseX, mouseY);
		}
	}
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String titleText = this.title.getFormattedText();
		this.font.drawString(titleText, this.xSize / 2 - this.font.getStringWidth(titleText) / 2, 4.0F, 0x404040);
		
	}
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		getMinecraft().getTextureManager().bindTexture(ALCHEMY_CALDRON_SCREEN_BACKGROUND);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(container.tileEntity.isBurning()) {
			this.blit(	this.guiLeft + 81, this.guiTop + 35,
						242, 0,
						14, 14
					);
			this.blit(	this.guiLeft + 38, this.guiTop + 73,
						0, 253,
						100 * container.tileEntity.burnTimer / container.tileEntity.fuelTime, 3
					);
		}
		
		if(container.tileEntity.onRefining()) {
			int progress = 99 - 99 * container.tileEntity.finishTimer / container.tileEntity.totalTime;
			
			this.blit(	this.guiLeft + 137, this.guiTop + 20,
						246 - (progress % 15) * 10, 246 - (progress / 15) * 10,
						10, 10
					);
			
			progress = 49 - 49 * container.tileEntity.finishTimer / container.tileEntity.totalTime;
			if(progress < 17) {
				this.blit(	this.guiLeft + 82, this.guiTop + 14,
						176 + (progress % 5) * 12, 0 + (progress / 5) * 22,
						12, 22
					);
			} else if(progress < 25) {
				progress -= 17;
				this.blit(	this.guiLeft + 82, this.guiTop + 14,
							188, 66,
							12, 22
						);
				this.blit(	this.guiLeft + 74, this.guiTop + 13,
							228, 66 + progress * 2,
							28, 2
						);
			} else if(progress < 42) {
				progress -= 25;
				this.blit(	this.guiLeft + 66, this.guiTop + 13,
							0, 166,
							44, 24
						);
				this.blit(	this.guiLeft + 66, this.guiTop + 14,
							176 + (progress % 10) * 8, 88 + (progress / 10) * 22,
							8, 22
						);
				this.blit(	this.guiLeft + 102, this.guiTop + 14,
							176 + (progress % 10) * 8, 132 + (progress / 10) * 22,
							8, 22
						);
			} else {
				progress -= 42;
				this.blit(	this.guiLeft + 66, this.guiTop + 13,
							44, 166,
							44, 23
						);
				this.blit(	this.guiLeft + 74, this.guiTop + 35,
							0, 190 + progress * 2,
							28, 2
						);
			}
		}
	}

}
