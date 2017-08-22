package monitoring.monitoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.RenderCallback;
import uk.co.caprica.vlcj.player.direct.RenderCallbackAdapter;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

public class VideoPlayer {

	private static final int width = 600;

	private static final int height = 400;

	private JFrame frame;

	private JPanel videoSurface;

	private BufferedImage image;

	private DirectMediaPlayerComponent mediaPlayerComponent;

	public VideoPlayer(){
		new NativeDiscovery().discover();

	}
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VideoPlayer().play("/Users/purushah/Documents/Ridhant's OCD.m4v");
			}
		});
	}

	public void play(String args) {
		frame = new JFrame("Direct Media Player");
		frame.setBounds(100, 100, width, height);
		videoSurface = new VideoSurfacePanel();
		frame.setContentPane(videoSurface);
		image = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()
				.createCompatibleImage(width, height);
		BufferFormatCallback bufferFormatCallback = new BufferFormatCallback() {
			public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
				return new RV32BufferFormat(width, height);
			}
		};
		mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback) {
			@Override
			protected RenderCallback onGetRenderCallback() {
				return new TutorialRenderCallbackAdapter();
			}
		};
		frame.setVisible(true);
		mediaPlayerComponent.getMediaPlayer().playMedia(args);
		mediaPlayerComponent.getMediaPlayer().stop();
		// controlsPanel = new PlayerControlsPanel(mediaPlayerComponent);

	}

	private class VideoSurfacePanel extends JPanel {

		private VideoSurfacePanel() {
			setBackground(Color.black);
			setOpaque(true);
			setPreferredSize(new Dimension(width, height));
			setMinimumSize(new Dimension(width, height));
			setMaximumSize(new Dimension(width, height));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, null, 0, 0);
		}
	}

	private class TutorialRenderCallbackAdapter extends RenderCallbackAdapter {

		private TutorialRenderCallbackAdapter() {
			super(new int[width * height]);
		}

		@Override
		protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
			// Simply copy buffer to the image and repaint
			image.setRGB(0, 0, width, height, rgbBuffer, 0, width);
			videoSurface.repaint();
		}
	}
}
