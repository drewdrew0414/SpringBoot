import { n as m, M as x, E as l, N as y, x as r, q as i, $ as g, Q as k, ax as C, y as I, p as c, an as $, aj as d } from "./copilot-C7bhBd6M.js";
import { r as w } from "./state-aTUQA9pO.js";
import { i as t } from "./icons-CJYK-fIm.js";
var E = Object.defineProperty, S = Object.getOwnPropertyDescriptor, b = (e, n, a, s) => {
  for (var o = s > 1 ? void 0 : s ? S(n, a) : n, p = e.length - 1, u; p >= 0; p--)
    (u = e[p]) && (o = (s ? u(n, a, o) : u(o)) || o);
  return s && o && E(n, a, o), o;
};
const _ = "bg-[linear-gradient(to_right,var(--amber-3),var(--amber-5),var(--amber-3),var(--amber-6))] dark:bg-[linear-gradient(to_right,var(--amber-5),var(--amber-7),var(--amber-5),var(--amber-8))]", P = "bg-[linear-gradient(to_right,var(--blue-3),var(--blue-5),var(--blue-3),var(--blue-6))] dark:bg-[linear-gradient(to_right,var(--blue-4),var(--blue-6),var(--blue-4),var(--blue-7))]", A = "bg-[linear-gradient(to_right,var(--ruby-3),var(--ruby-5),var(--ruby-3),var(--ruby-6))] dark:bg-[linear-gradient(to_right,var(--ruby-4),var(--ruby-6),var(--ruby-4),var(--ruby-7))]", L = "bg-[linear-gradient(to_right,var(--teal-3),var(--teal-5),var(--teal-3),var(--teal-6))] dark:bg-[linear-gradient(to_right,var(--teal-4),var(--teal-6),var(--teal-4),var(--teal-7))]";
let v = class extends x {
  constructor() {
    super(...arguments), this._helpExpanded = !1;
  }
  createRenderRoot() {
    return this;
  }
  connectedCallback() {
    super.connectedCallback(), this.classList.add("flex", "flex-col");
  }
  render() {
    return r`
      <header class="flex items-center pe-2 ps-4 py-2">
        <h2 class="font-bold gap-1 me-auto my-0 text-xs uppercase">Vaadin Copilot</h2>
        <vaadin-button
          aria-label="Close"
          theme="icon tertiary"
          @click=${() => {
      this.closePopover();
    }}>
          <vaadin-icon .svg="${t.close}"></vaadin-icon>
          <vaadin-tooltip slot="tooltip" text="Close"></vaadin-tooltip>
        </vaadin-button>
      </header>
      <div class="flex flex-col gap-4 pb-4 px-4">
        ${this.renderUserButton()} ${this.renderDevelopmentWorkflow()} ${this.renderWelcomeToVersion()}
        <div class="bg-gray-3 dark:bg-gray-6 flex flex-col rounded-md">
          <vaadin-button
            @click="${this.handleAppInfoClick}"
            class="border-0 h-auto justify-start py-2"
            theme="tertiary">
            <vaadin-icon slot="prefix" .svg="${t.info}"></vaadin-icon>
            App Info
          </vaadin-button>
          <vaadin-button @click="${this.handleAppLogClick}" class="border-0 h-auto justify-start py-2" theme="tertiary">
            <vaadin-icon slot="prefix" .svg="${t.terminal}"></vaadin-icon>
            App Log
          </vaadin-button>
          <vaadin-button
            @click="${this.handleFeaturesClick}"
            class="border-0 h-auto justify-start py-2"
            theme="tertiary">
            <vaadin-icon slot="prefix" .svg="${t.listAlt}"></vaadin-icon>
            Features
          </vaadin-button>
          ${y.springSecurityEnabled ? r`
                <vaadin-button
                  @click="${this.handleImpersonateAppUserClick}"
                  class="border-0 h-auto justify-start py-2"
                  theme="tertiary">
                  <vaadin-icon slot="prefix" .svg="${t.accountCircle}"></vaadin-icon>
                  Impersonate App User
                </vaadin-button>
              ` : l}
        </div>
        <div class="bg-gray-3 dark:bg-gray-6 flex flex-col rounded-md">
          <vaadin-button
            @click="${this.handleFeedbackClick}"
            class="border-0 h-auto justify-start py-2"
            theme="tertiary">
            <vaadin-icon slot="prefix" .svg="${t.feedback}"></vaadin-icon>
            Feedback
          </vaadin-button>
          <vaadin-button
            @click="${this.toggleHelpAndSupport}"
            class="border-0 h-auto justify-start py-2"
            theme="tertiary">
            <vaadin-icon slot="prefix" .svg="${t.help}"></vaadin-icon>
            Help & Support
            <vaadin-icon
              slot="suffix"
              .svg="${this._helpExpanded ? t.keyboardArrowUp : t.keyboardArrowDown}"></vaadin-icon>
          </vaadin-button>
          ${this._helpExpanded ? this.renderHelpLinks() : l}
          <vaadin-button
            @click="${this.handleSettingsClick}"
            class="border-0 h-auto justify-start py-2"
            theme="tertiary">
            <vaadin-icon slot="prefix" .svg="${t.settings}"></vaadin-icon>
            Settings
          </vaadin-button>
        </div>
      </div>
    `;
  }
  renderUserButton() {
    const e = i.userInfo?.validLicense, n = e ? _ : P, a = e ? "text-amber-12 dark:text-amber-11" : "text-blue-12 dark:text-blue-11", s = this.getUserName() !== "Log in";
    return r`
      <vaadin-button
        @click=${this.handleUserLoginClick}
        class="animate-gradient ${n} border-0 h-auto justify-start py-2 text-start ${s ? "gap-3 px-3" : "items-start"}">
        ${s ? this.renderUserImage() : r`<vaadin-icon
              class="text-blue-12 dark:text-blue-11"
              slot="prefix"
              .svg="${t.login}"></vaadin-icon>`}
        <span class="flex flex-col">
          <span>${this.getUserName()}</span>
          <span class="${a} text-xs">${this.getLicenseType()}</span>
        </span>
      </vaadin-button>
    `;
  }
  renderWelcomeToVersion() {
    const e = i.projectVersionReleaseNoteInfo;
    return e === null ? l : g.getMostRecentReleaseNoteDismissed() ? l : e.mostRecentVersion ? e.url ? r`
      <div class="flex relative">
      <vaadin-button
        id="release-note-btn"
        data-test-id="release-note-btn"
        class="border-0 h-auto items-start justify-start px-3 py-2 text-start w-full"
        @click="${(n) => {
      window.open(e.url, "_blank");
    }}">
        <vaadin-icon class="text-blue-11" slot="prefix" .svg="${t.info}"></vaadin-icon>
        <span class="flex flex-col">
          <span>Welcome to Vaadin ${e.vaadinVersion}</span>
          <span class="text-blue-11 text-xs">Click for release notes</span>
        </span>
      </vaadin-button>
      <vaadin-button
        class="absolute end-0 top-0"
        id="dismiss-release-note-item"
        theme="icon tertiary"
        @click="${(n) => {
      n.stopPropagation(), g.setMostRecentReleaseNoteDismissed(!0);
    }}"
        ><vaadin-icon .svg="${t.close}"></vaadin-icon
        <vaadin-tooltip slot="tooltip" text="Dismiss"></vaadin-tooltip>
      </vaadin-button>
      </div>
    ` : l : l;
  }
  renderUserImage() {
    return i.userInfo?.portraitUrl ? r`<img
        alt="${this.getUserName()}"
        class="rounded-full size-8 object-cover"
        slot="prefix"
        src="https://vaadin.com${i.userInfo.portraitUrl}" />` : l;
  }
  renderDevelopmentWorkflow() {
    const e = k(), n = C(), a = this.getDevelopmentWorkflowConfig(e, n), s = a?.bgClass ?? "", o = a?.colorClass ?? "", p = this.resolveIcon(a), u = a?.rotateIcon ? `rotate-180 ${o}` : o, h = this.resolveTitle(a), f = a?.displayMessage ?? "";
    return r`
      <vaadin-button
        data-test-id="development-workflow-btn"
        @click="${this.handleDevelopmentWorkflowClick}"
        class="animation-delay-4000 animate-gradient ${s} border-0 h-auto items-start justify-start py-2 text-start">
        <vaadin-icon class="${u}" slot="prefix" .svg="${p}"></vaadin-icon>
        <span class="flex flex-col">
          <span>${h}</span>
          <span class="text-xs ${o}">${f}</span>
        </span>
      </vaadin-button>
    `;
  }
  getDevelopmentWorkflowConfig(e, n) {
    const a = {
      bgClass: L,
      colorClass: "text-teal-11"
    };
    if (e === "warning" && n === "warning")
      return {
        ...a,
        icon: t.wbIncandescent,
        rotateIcon: !0,
        title: "IDE plugin & Hotswap recommended",
        combinedTitle: !0,
        displayMessage: "Enable both for optimal development workflow"
      };
    if (e === "warning")
      return {
        ...a,
        icon: t.wbIncandescent,
        rotateIcon: !0,
        title: "Hotswap recommended",
        displayMessage: "Applies changes without restarting"
      };
    if (n === "warning")
      return {
        ...a,
        icon: t.code,
        getIcon: !0,
        title: "IDE plugin recommended",
        getTitle: !0,
        displayMessage: "Simplifies Hotswap setup & config"
      };
    if (e === "error")
      return {
        bgClass: A,
        colorClass: "text-ruby-11",
        icon: t.error,
        title: "Hotswap partially enabled",
        displayMessage: "View details"
      };
  }
  resolveIcon(e) {
    return e ? e.getIcon ? this.getIdeIcon() : e.icon : t.bolt;
  }
  resolveTitle(e) {
    return e ? e.combinedTitle ? this.getCombinedTitle() : e.getTitle ? this.getIdePluginName() : e.title : "Development Workflow";
  }
  getUserName() {
    return [i.userInfo?.firstName, i.userInfo?.lastName].filter(Boolean).join(" ") || "Log in";
  }
  getLicenseType() {
    return i.userInfo?.validLicense ? "" : "Unlock all Copilot features, including AI";
  }
  getIdeIcon() {
    switch (i.idePluginState?.ide) {
      case "intellij":
        return t.intelliJ;
      case "vscode":
        return t.vsCode;
      case "eclipse":
        return t.eclipse;
      default:
        return t.code;
    }
  }
  getIdePluginName() {
    switch (i.idePluginState?.ide) {
      case "intellij":
        return "Vaadin plugin for IntelliJ";
      case "vscode":
        return "Vaadin extension for VS Code";
      case "eclipse":
        return "Vaadin plugin for Eclipse";
      default:
        return "IDE plugin";
    }
  }
  getCombinedTitle() {
    switch (i.idePluginState?.ide) {
      case "intellij":
        return "IntelliJ plugin & Hotswap recommended";
      case "vscode":
        return "VS Code extension & Hotswap recommended";
      case "eclipse":
        return "Eclipse plugin & Hotswap recommended";
      default:
        return "IDE plugin & Hotswap recommended";
    }
  }
  closePopover() {
    const e = this.closest("vaadin-popover");
    e && (e.opened = !1);
  }
  handleUserLoginClick() {
    if (i.userInfo?.validLicense) {
      window.open("https://vaadin.com/myaccount", "_blank", "noopener");
      return;
    }
    i.setLoginCheckActive(!0);
  }
  handleDevelopmentWorkflowClick() {
    I("use-dev-workflow-guide"), c.openPanel($), this.closePopover();
  }
  handleAppInfoClick() {
    c.openPanel(d.INFO), this.closePopover();
  }
  handleAppLogClick() {
    c.openPanel(d.LOG), this.closePopover();
  }
  handleFeaturesClick() {
    c.openPanel(d.FEATURES), this.closePopover();
  }
  handleImpersonateAppUserClick() {
    c.openPanel(d.IMPERSONATOR), this.closePopover();
  }
  handleSettingsClick() {
    c.openPanel(d.SETTINGS), this.closePopover();
  }
  handleFeedbackClick() {
    c.openPanel(d.FEEDBACK), this.closePopover();
  }
  toggleHelpAndSupport() {
    this._helpExpanded = !this._helpExpanded;
  }
  renderHelpLinks() {
    return r`
      <div class="flex flex-col ps-4">
        ${[
      { label: "Forum", icon: "forum", url: "https://vaadin.com/forum" },
      { label: "Docs", icon: "article", url: "https://vaadin.com/docs/latest/tools/copilot" },
      { label: "GitHub Issues", icon: "github", url: "https://github.com/vaadin/copilot/issues" }
    ].map(
      ({ label: n, icon: a, url: s }) => r`
            <vaadin-button
              @click="${() => window.open(s, "_blank", "noopener")}"
              class="border-0 h-auto justify-start py-2"
              theme="tertiary">
              <vaadin-icon slot="prefix" .svg="${t[a]}"></vaadin-icon>
              ${n}
            </vaadin-button>
          `
    )}
      </div>
    `;
  }
};
b([
  w()
], v.prototype, "_helpExpanded", 2);
v = b([
  m("copilot-devtools")
], v);
export {
  v as CopilotDevTools
};
