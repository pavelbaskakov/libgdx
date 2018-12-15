package com.mygdx.game.chapter7.jumpingjack2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.chapter7.base.BaseGame;
import com.mygdx.game.chapter7.base.BaseScreen;
import com.mygdx.game.chapter7.base.Box2DActor;
import com.mygdx.game.chapter7.base.util.GameUtils;
import com.mygdx.game.chapter7.jumpingjack.Coin;
import com.mygdx.game.chapter7.jumpingjack.Player;
import com.mygdx.game.chapter7.starscape.ParticleActor;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {
    private Player player;
    private World world;
    private ArrayList<Box2DActor> removeList;
    private ParticleActor baseSparkle;
    TiledMap tiledMap;
    OrthographicCamera tiledCamera;
    TiledMapRenderer tiledMapRenderer;
    int[] backgroundLayer = {0};
    int[] tileLayer = {1};
    // game world dimensions
    final int mapWidth = 1280; // bigger than before!
    final int mapHeight = 600;
    public GameScreen(BaseGame g) {
        super(g);
    }

    public void create() {
        world = new World(new Vector2(0, -9.8f), true);
        removeList = new ArrayList<>();
        // background image provided by tilemap
        // player
        player = new Player();
        Animation walkAnim = GameUtils.parseImageFiles(
                "jumpingjack2/walk-", ".png", 3, 0.15f,
                Animation.PlayMode.LOOP_PINGPONG);
        player.storeAnimation( "walk", walkAnim );
        Texture standTex = new Texture(Gdx.files.internal("jumpingjack2/stand.png"));
        standTex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player.storeAnimation( "stand", standTex );
        Texture jumpTex = new Texture(Gdx.files.internal("jumpingjack2/jump.png"));
        jumpTex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player.storeAnimation( "jump", jumpTex );
        player.setSize(60,90);
        mainStage.addActor(player);
        // set other player properties later…
        // coin
        Coin baseCoin = new Coin();
        Texture coinTex = new Texture(Gdx.files.internal("jumpingjack2/coin.png"));
        coinTex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        baseCoin.storeAnimation( "default", coinTex );
        baseSparkle = new ParticleActor();
        baseSparkle.load("jumpingjack2/sparkler.pfx", "jumpingjack2/");

        // load tilemap
        tiledMap = new TmxMapLoader().load("jumpingjack2/platform-map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, viewWidth, viewHeight);
        tiledCamera.update();

        MapObjects objects = tiledMap.getLayers().get("ObjectData").getObjects();
        for (MapObject object : objects) {
            String name = object.getName();
            // all object data assumed to be stored as rectangles
            RectangleMapObject rectangleObject = (RectangleMapObject)object;
            Rectangle r = rectangleObject.getRectangle();
            if (name.equals("player")) {
                player.setPosition( r.x, r.y );
            } else if ( name.equals("coin") ) {
                Coin coin = baseCoin.clone();
                coin.setPosition(r.x, r.y);
                mainStage.addActor(coin);
                coin.initializePhysics(world);
            } else {
                System.err.println("Unknown tilemap object: " + name);
            }
        }

        player.setDynamic();
        player.setShapeRectangle();
        player.setPhysicsProperties(1, 0.5f, 0.0f);
        player.setMaxSpeedX(2);
        player.setFixedRotation();
        player.initializePhysics(world);

        objects = tiledMap.getLayers().get("PhysicsData").getObjects();
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject)
                addSolid( (RectangleMapObject)object );
            else
                System.err.println("Unknown PhysicsData object.");
        }

        world.setContactListener(new ContactListenerImpl(removeList, mainStage, baseSparkle));
    }

    @Override
    public void render(float dt) {
        uiStage.act(dt);
        // only pause gameplay events, not UI events
        if ( !isPaused() ) {
            mainStage.act(dt);
            update(dt);
        }
        // render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera mainCamera = mainStage.getCamera();
        mainCamera.position.x = player.getX()
                + player.getOriginX();
        // bound main camera to layout
        mainCamera.position.x = MathUtils.clamp(
                mainCamera.position.x, viewWidth/2, mapWidth
                        - viewWidth/2);
        mainCamera.update();
        // scroll background more slowly to create parallax effect
        tiledCamera.position.x = mainCamera.position.x/4
                + mapWidth/4;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);
        tiledMapRenderer.render(backgroundLayer);
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);
        tiledMapRenderer.render(tileLayer);
        mainStage.draw();
        uiStage.draw();
    }

    public void update(float dt) {
        removeList.clear();
        world.step(1/60f, 6, 2);
        for (Box2DActor ba : removeList) {
            ba.destroy();
            world.destroyBody( ba.getBody() );
        }

        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            player.setScale(-1,1);
            player.applyForce( new Vector2(-3.0f, 0) );
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
            player.setScale(1,1);
            player.applyForce( new Vector2(3.0f, 0) );
        }
        if (player.getSpeed() > 0.1 && player.getAnimationName().equals("stand") )
            player.setActiveAnimation("walk");
        if (player.getSpeed() < 0.1 && player.getAnimationName().equals("walk") )
            player.setActiveAnimation("stand");
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.P)
            togglePaused();
        if (keycode == Input.Keys.R)
            game.setScreen( new GameScreen(game));
        if (keycode == Input.Keys.SPACE && player.isOnGround() ) {
            Vector2 jumpVec = new Vector2(0,3);
            player.applyImpulse(jumpVec);
            player.setActiveAnimation("jump");
        }
        return false;
    }

    public void addSolid(RectangleMapObject rmo) {
        Rectangle r = rmo.getRectangle();
        Box2DActor solid = new Box2DActor();
        solid.setPosition(r.x, r.y);
        solid.setSize(r.width, r.height);
        solid.setStatic();
        solid.setShapeRectangle();
        solid.initializePhysics(world);
    }

    static class ContactListenerImpl implements ContactListener {
        private ArrayList<Box2DActor> removeList;
        private Stage mainStage;
        private ParticleActor baseSparkle;

        public ContactListenerImpl(ArrayList<Box2DActor> removeList, Stage mainStage, ParticleActor baseSparkle) {
            this.removeList = removeList;
            this.mainStage = mainStage;
            this.baseSparkle = baseSparkle;
        }

        public void beginContact(Contact contact) {
            Object objC = GameUtils.getContactObject(contact, Coin.class);
            if (objC != null) {
                Object objP = GameUtils.getContactObject(contact, Player.class, "main");
                if (objP != null) {
                    Coin c = (Coin)objC;
                    removeList.add( c );
                    ParticleActor sparkle = baseSparkle.clone();
                    sparkle.setPosition(c.getX() + c.getOriginX(), c.getY() + c.getOriginY() );
                    sparkle.start();
                    mainStage.addActor(sparkle);
                }
                return; // avoid possible jumps
            }
            Object objP = GameUtils.getContactObject(contact, Player.class, "bottom");
            if ( objP != null ) {
                Player p = (Player)objP;
                p.adjustGroundCount(1);
                p.setActiveAnimation("stand");
            }
        }

        public void endContact(Contact contact) {
            Object objC = GameUtils.getContactObject(contact, Coin.class);
            if (objC != null)
                return;
            Object objP = GameUtils.getContactObject(contact, Player.class, "bottom");
            if ( objP != null ) {
                Player p = (Player)objP;
                p.adjustGroundCount(-1);
            }
        }
        public void preSolve(Contact contact, Manifold oldManifold) { }
        public void postSolve(Contact contact, ContactImpulse impulse) { }
    }
}
